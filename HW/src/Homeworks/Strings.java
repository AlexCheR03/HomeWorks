package Homeworks;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Strings {
    public static HashMap<String, Integer> dict = new HashMap<>();     //hashmap realizing
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
    public static void clearword(String word) {                   //причесываем слово
        int i = word.length() - 1;
        String c = word.substring(i);
        while (i >= 0) {
            if (".,!:?;\'\"/|(){}[]+-".contains(c)) {
                if (dict.containsKey(c)) {                    //Если поледний символ это не буква
                    dict.put(c, dict.get(c) + 1);
                } else {
                    dict.put(c, 1);                             //добавление элемента или прибавление к уже имеющемуся элементу 1
                }
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
                while (i >= 0 && !(".,!:?;\'\"|/(){}[]+-".contains(c))) {     //идём до первого не алфавитного символа (можно было через for)
                    char_counter++;
                    i--;
                    if (i == -1) {
                        c = word.substring(0, 0);
                    } else {
                        c = word.substring(i, i + 1);
                    }
                }

                String subword = word.substring(i + 1);
                if (dict.containsKey(subword)) {
                    dict.put(subword, dict.get(subword) + 1);
                } else {
                    dict.put(subword, 1);                             //добавление слова или прибавление к уже имеющемуся слову 1
                }
                word = word.substring(0, i + 1);

            }


        }
    }

    public static void printDict(HashMap<String, Integer> dict) {
        System.out.println("------Все слова из файла и сколько раз они встретились------");
        for (Map.Entry<String, Integer> entry: dict.entrySet()) {
            word_counter += entry.getValue();
            System.out.println("\t" + "\"" + entry.getKey() + "\"" + ": " + entry.getValue());
        }
        System.out.println("\nВсего слов в файле: " + word_counter);
        System.out.println("\nВсего символов в файле: " + char_counter);

    }


}
