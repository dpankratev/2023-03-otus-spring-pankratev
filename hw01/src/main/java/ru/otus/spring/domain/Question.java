package ru.otus.spring.domain;

import lombok.Getter;

public class Question {

    @Getter
    private final String question;

    @Getter
    private final int id;

    public Question(int id, String question) {
        this.id = id;
        this.question = question;
    }
}
