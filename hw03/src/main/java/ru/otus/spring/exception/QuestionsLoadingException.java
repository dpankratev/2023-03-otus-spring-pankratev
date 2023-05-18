package ru.otus.spring.exception;

public class QuestionsLoadingException extends RuntimeException {

    public QuestionsLoadingException(Throwable cause) {
        super(cause);
    }
}
