package Homeworks;

public class sort {
    public static void main(String[] args) {
        int[] arr = {1, 3, 20, 0, -10, 11, 1, 5}; //length is 5

        printArr(sortV1(arr));
    }



    public static int[] sortV1(int[] arr) {      // O(n^2)
        int l = arr.length;
        for (int i = 1; i < l; i++) {   // total iterations is len - 1
            int r_i = l - i;        //right index in the current iteration

            int m_p = 0;                  //the maximum position
            int m = arr[0];            //variable for finding the maximum in the current iteration
            for (int j = 1; j <= r_i; j++) {     //finding maximum in arr[0 ; r_i]
                if (arr[j] > m) {
                    m = arr[j];
                    m_p = j;
                }
            }

            if (arr[r_i] != m) {            //Проверка на равенство максимума и самого правого числа на текущей итерации
                int interim = arr[r_i];     //создаём временную переменную для последнего символа на итерации
                arr[r_i] = m;               //меняем местами самый правый элемент и максимальный, если они не равны
                arr[m_p] = interim;
            }
        }

        return arr;
    }

    public static void printArr(int[] arr) {
        for (int el: arr) {
            System.out.println(el);
        }
    }
}


