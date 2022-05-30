package ru.geekbrains;

/**
 *Java2. HomeWork2
 *
 * @autor Andrey Zhilenkov
 * @ version 14.10.2021
 */

public class Main {

    public static void main(String[] args) {
        String[][] index = {{"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"2", "3", "4", "5"},
                {"6", "7", "8", "9"}};
        System.out.printf("Summa of array's index = %d",check(index));
    }

    public static int check(String[][] index) {

        if (index.length != 4) {
            throw new MyArraySizeException();
        }

        for (int i = 0; i <= 3; i++) {
            if (index[i].length != 4) {
                throw new MyArraySizeException();
            }
        }

        int sum = 0;
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                try {
                    sum += Integer.parseInt(index[i][j]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    throw new MyArrayDataException(i, j);
                }

            }
        }
        return sum;
    }
}
