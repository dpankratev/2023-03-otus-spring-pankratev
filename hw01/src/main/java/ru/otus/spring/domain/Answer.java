package ru.otus.spring.domain;

import lombok.Getter;

public class Answer {

    @Getter
    private final boolean isRight;

    @Getter
    private final String answer;

    public Answer(String answer, boolean isRight) {
        this.answer = answer;
        this.isRight = isRight;
    }
}
