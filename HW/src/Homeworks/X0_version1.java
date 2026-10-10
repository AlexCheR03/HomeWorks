package Homeworks;

import java.util.Scanner;

public class X0_version1 {
    public static void main(String[] args) {
        int fill = 0; // храним в каждом бите факт заполненности поля(0/1)
        int value = 0; // храним в каждом бите чем заполнено (0/1)

        int row = 0, col = 0;
        int shift = 0;

        Scanner in = new Scanner(System.in);

        int i = 1;
        while (i <= 9) {
            //всего (максимум) 9 ходов
            System.out.println("Ход номер " + i);

            if (i % 2 != 0) {  //игрок ходит
                System.out.print("Введите номер строки: ");
                row = in.nextInt();
                System.out.print("Введите номер столбца: ");
                col = in.nextInt();

                shift = (3 - row) * 3 + (3 - col); //вычисление сдвига и установка значения на сетку для игрока
                int a = 1 << shift;
                fill = fill | a;
                value = value | a;
            }

            else {   //компуктер ходит
                shift = 0;      //проходимся по каждой ячейке и ищем первую свободную
                while(true) {
                    int mask = 1 << shift;
                    if ((fill & mask) > 0) {
                        shift++;
                    }
                    else {
                        break;
                    }
                }
                int a = 1 << shift;        //вычисление сдвига и установка значения на сетку компьютера
                fill = fill | a;
                System.out.println("Компуктер поставил нолик");
            }

            printField(value, fill, shift);
            //логика проверки победителя
            if (i > 4) {
                if (
                        checkRowWin(value, fill, 1) | checkRowWin(value, fill, 2) | checkRowWin(value, fill, 3) |
                                checkColWin(value, fill, 1) | checkColWin(value, fill, 2) | checkColWin(value, fill, 3) |
                                checkMainDiagonalWin(value, fill) | checkSecondaryDiagonalWin(value, fill)
                ) {
                    if (i % 2 != 0) {
                        System.out.println("Игрок победил!");
                    }
                    else {
                        System.out.println("Компьютер победил!");
                    }
                    System.exit(130);
                }
            }
            i += 1;
        }
        System.out.println("Ничья!");
    }


    public static void printField(int value, int fill, int shift){
        //Вывод поля
        //Определяем заполненность поля
        for (int r = 1; r <= 3; r++) {
            for (int co = 1; co <= 3; co ++) {
                shift = (3 - r) * 3 + (3 - co);
                char c = ' ';
                int mask = 1 << shift;     // 1, поставленная в нужную позицию
                if ((fill & mask) > 0) {     //если клетка заполнена
                    if ((value & mask) > 0) {
                        c = 'X';
                    }
                    else {
                        c = '0';
                    }
                }
                else {
                    c = '_';
                }
                System.out.print(" " + c + " ");
            }
            System.out.println();
        }
    }

    public static boolean checkRowWin(int value, int fill, int row) {
        int shiftRow = (3 - row) * 3 + 2;   //вычисление сдвига в начало заданной строки
        int mask = 1 << shiftRow;

        if (((fill & mask) > 0) && ((fill & (mask >> 1)) > 0) && ((fill & (mask >> 2)) > 0)) { //проверка заполненности строки
            if ( ((value >> shiftRow) & 0b1) == ((value >> shiftRow - 1) & 0b1) & ((value >> shiftRow) & 0b1) == ((value >> shiftRow - 2) & 0b1)  ) { //провверка на одинаковые символы
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    public static boolean checkColWin(int value, int fill, int col) {
        int shiftCol = 8 - (col - 1);
        int mask = 1 << shiftCol;
        if (((fill & mask) > 0) & ((fill & mask >> 3) > 0) & ((fill & mask >> 6) > 0)) {
            if ( (((value >> shiftCol) & 0b1) == ((value >> shiftCol - 3) & 0b1)) & ((value >> shiftCol) & 0b1) == ((value >> shiftCol - 6) & 0b1) ) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    public static boolean checkMainDiagonalWin(int value, int fill) {
        int mask = 1 << 6;
        if ( ((fill & mask) > 0) & ((fill & mask >> 4) > 0) & ((fill & mask >> 8) > 0) ) {
            if ( ((value >> 8 & 0b1) == ((value >> 4) & 0b1)) & (((value >> 8) & 0b1) == (value & 0b1)) ) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    public static boolean checkSecondaryDiagonalWin(int value, int fill) {
        int mask = 1 << 6;

        if ( ((fill & mask) > 0) & ((fill & mask >> 2) > 0) & ((fill & mask) > 0 >> 4) ) {
            if ( (((value >> 6) & 0b1) == ((value >> 4) & 0b1)) & (((value >> 6) & 0b1) == ((value >> 2) & 0b1)) ) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }


}