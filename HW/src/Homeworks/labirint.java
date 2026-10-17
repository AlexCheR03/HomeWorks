package Homeworks;

import java.util.Scanner;

public class labirint {


    //шаги
    // определение структуры данных
     // отрисовка текущ. лабиринта
     // ввыод направления с проверкой
     //пересчет позиции и состояния

    public static int[][] field = {{1, 0, 1, 1, 1},
                            {1, 0, 0, 1, 1},
                            {1, 1, 0, 1, 1},
                            {1, 1, 0, 0, 1},
                            {1, 1, 1, 0, 1}};

    public static char[][] visibleField = {{'-', '*', '-', '-', '-'},
                                            {'-', '-', '-', '-', '-'},
                                            {'-', '-', '-', '-', '-'},
                                            {'-', '-', '-', '-', '-'},
                                            {'-', '-', '-', '-', '-'}};
    public static int rowPos = 0;
    public static int colPos = 1;
    public static int stepCounter = 0;
    public static int rowExit = 0;
    public static int colExit = 0;

    public static void main(String[] args) {
        printField();
        String direction = inputDirection();
        switch (direction){
            case "u":
                if (rowPos > 0 && field[rowPos - 1][colPos] == 0) {
                    visibleField[rowPos][colPos] = '#';
                    rowPos--;
                    visibleField[rowPos - 1][colPos] = '*';
                }
        }



    }

    public static void printField() {
        for(int i = 0; i < visibleField.length; i++) {
            for(int j = 0; j < visibleField[0].length; j++) {
                System.out.print(visibleField[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static String inputDirection() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("Введите направление: ");
            String direction = scan.nextLine().toLowerCase();
            if ((direction.equals("u") || direction.equals("d") || direction.equals("l") || direction.equals("r"))) {
                stepCounter++;
                return direction;


            }
            else {
                System.out.println("Вы ввели недопустимый символ! Допустимо - u, d, l, r");
            }
        }
    }


}
