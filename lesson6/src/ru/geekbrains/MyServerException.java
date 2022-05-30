package ru.geekbrains;

public class MyServerException extends RuntimeException {

    public MyServerException(String message, Throwable cause) {
        super(message, cause);
    }
}

