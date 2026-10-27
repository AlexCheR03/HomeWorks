package Homeworks;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Strings {

    public static String[][] dict = new String[21000][2];   //аналог хэшмапы
    public static int dictSize = 0;    // Переменная для контроля реального количества уникальных элементов в массиве

    public static int char_counter = 0;
    public static int word_counter = 0;
    public static int max_len_line = 0;

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("C://Users//San4ez//IdeaProjects//LEARNING//HW//src//Homeworks//labirinth.java"));

        while (scan.hasNextLine()) {
            String line = scan.nextLine().trim();
            if (line.length() > max_len_line)  //макс длинна строки
                max_len_line = line.length();

            for(String word: line.split(" ")) {
                if (!line.equals(""))
                    clearword(word);
            }
        }
        printDict(dict);
    }




    public static void putToDict(String key) {
        for (int i = 0; i < dictSize; i++) {
            if (dict[i][0].equals(key)) {    // Проверяем, есть ли уже такое слово в заполненной части массива
                int count = Integer.parseInt(dict[i][1]);   // Если нашли: переводим значение в инт, прибавляем 1 и обратно в строку
                dict[i][1] = String.valueOf(count + 1);
                return; //если нашли, то завершаем досрочно
            }
        }

        if (dictSize < dict.length) {    // Если слова нет в списке, создаем новый элемент
            dict[dictSize][0] = key;
            dict[dictSize][1] = "1"; // Изначально встретилось 1 раз
            dictSize++;
        }
    }

    public static void clearword(String word) {                   //причесываем слово
        int i = word.length() - 1;
        String c = word.substring(i);
        while (i >= 0) {
            if (".,!:?;\'\"/|(){}[]+-=".contains(c)) {
                putToDict(c);
                char_counter++;
                i--;

                word = word.substring(0, i + 1);
                if (i == -1) {
                    c = word.substring(0, 0);
                } else {
                    c = word.substring(i, i + 1);
                }
            }

            else if (c.equals(" ")) {                           //Если поледний символ это пробел
                i--;
                word = word.substring(0, i + 1);
                c = word.substring(i);
            }

            else {
                while (i >= 0 && !(".,!:?;\'\"|/(){}[]+-".contains(c))) {     //идём до первого не алфавитного символа
                    char_counter++;
                    i--;
                    if (i == -1) {
                        c = word.substring(0, 0);
                    } else {
                        c = word.substring(i, i + 1);
                    }
                }

                String subword = word.substring(i + 1);
                putToDict(subword);
                word = word.substring(0, i + 1);
            }
        }
    }

    public static void printDict(String[][] dict) {
        System.out.println("------Все слова из файла и сколько раз они встретились------");
        for (int i = 0; i < dictSize; i++) {  //идем только по заполненным ячейкам
            int value = Integer.parseInt(dict[i][1]);
            word_counter += value;
            System.out.println("\t" + "\"" + dict[i][0] + "\"" + ": " + value);
        }
        System.out.println("\nВсего слов в файле: " + word_counter);
        System.out.println("\nВсего символов в файле: " + char_counter);
    }
}
