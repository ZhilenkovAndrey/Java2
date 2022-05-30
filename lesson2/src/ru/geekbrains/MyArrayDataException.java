package ru.geekbrains;

public class MyArrayDataException extends RuntimeException{

    private int vertical;
    private int horizontal;

    public MyArrayDataException(int horizontal, int vertical) {
        super("Wrong index column " + (vertical + 1) + ", line " + (horizontal + 1));
        this.vertical = vertical;
        this.horizontal = horizontal;

    }
}