package ru.geekbrains;

/**
 *Java2. HomeWork5
 *
 * @autor Andrey Zhilenkov
 * @ version 24.10.2021
 */

public class Main {
    static final int size = 10000000;
    static final int halfSize = size / 2;

    public static void main(String[] args) throws InterruptedException {
        metod1();
        metod2();
    }

    public static float[] calculation(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) *
                    Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return arr;
    }

    public static void metod1() {
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long a = System.currentTimeMillis();
        calculation(arr);
        System.out.println("One thread time: " + (System.currentTimeMillis() - a) + " ms.");
    }

    public static void metod2() throws InterruptedException {
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long a = System.currentTimeMillis();

        float[] leftHalf = new float[arr.length / 2 - 1];
        float[] rightHalf = new float[arr.length / 2 - 1];
        System.arraycopy(arr, 0, leftHalf, 0, halfSize - 1);
        System.arraycopy(arr, halfSize - 1, rightHalf, 0, halfSize - 1);

        Thread thread1 = new Thread(() -> {
            calculation(leftHalf);
        });
        thread1.start();
        thread1.join();

        Thread thread2 = new Thread(() -> {
            calculation(rightHalf);
        });
        thread2.start();
        thread2.join();

        float[] mergedArray = new float[size];
        System.arraycopy(leftHalf, 0, mergedArray, 0, arr.length / 2 - 1);
        System.arraycopy(rightHalf, 0, mergedArray, arr.length / 2 - 1, arr.length / 2 - 1);
        System.out.println("Two thread time: " + (System.currentTimeMillis() - a) + " ms.");
    }
}

