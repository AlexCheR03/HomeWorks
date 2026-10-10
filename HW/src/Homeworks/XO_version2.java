package Homeworks;

import java.util.Random;
import java.util.Scanner;


public class XO_version2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        int[][] board = new int[3][3];

        int motion = 1;
        while (true) {
            if (motion % 2 != 0) {
                // Ход Игрока
                System.out.print("Ход игрока (Ход №" + motion + "). Введите строку и столбец (1-3):");
                int row = scan.nextInt() - 1;
                int col = scan.nextInt() - 1;

                if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != 0) {
                    System.out.println("Неверный ход или клетка занята! Попробуйте еще раз.");
                    continue;
                }
                board[row][col] = 1;

            } else {
                // Ход Компьютера
                System.out.println("Ход компьютера (Ход №" + motion + ")...");

                int compRow = random.nextInt(3);
                int compCol = random.nextInt(3);

                while (board[compRow][compCol] != 0) {
                    compRow = random.nextInt(3);
                    compCol = random.nextInt(3);
                }
                board[compRow][compCol] = 2;
            }


            printField(board);

            // ПРОВЕРКА НА НАЛИЧИЕ ОДИНАКОВОЙ СТРОКИ, СТОЛБЦА ИЛИ ДИАГОНАЛИ
            boolean hasLine = false;

            // Проверка строк
            for (int i = 0; i < 3; i++) {
                if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                    hasLine = true;
                }
            }
            // Проверка столбцов
            for (int i = 0; i < 3; i++) {
                if (board[0][i] != 0 && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                    hasLine = true;
                }
            }
            // Проверка диагоналей
            if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
                hasLine = true;
            }
            if (board[0][2] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
                hasLine = true;
            }


            if (hasLine) {
                if (motion % 2 != 0) {
                    System.out.println("Победил Игрок!");
                } else {
                    System.out.println("Победил Компьютер!");
                }
                break;
            }

            // Проверка на ничью
            if (motion == 9) {
                System.out.println("Ничья! Свободных клеток нет.");
                break;
            }

            motion++;
        }
    }

    public static void printField(int[][] field) {
        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[i].length; j++) {
                switch (field[i][j]) {
                    case (1):
                        System.out.print("X ");
                        break;
                    case (2):
                        System.out.print("0 ");
                        break;
                    default:
                        System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }


}
