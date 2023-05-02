package ru.otus.spring.exception;

public class IncorrectNumberAnswer extends RuntimeException {

    public IncorrectNumberAnswer(String message, Throwable cause) {
        super(message, cause);
    }
}
