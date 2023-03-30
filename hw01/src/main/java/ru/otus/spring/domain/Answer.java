package ru.otus.spring.domain;

import lombok.Getter;

public class Answer {

    @Getter
    private final boolean isRight;

    @Getter
    private final String answer;

    @Getter
    private final int questionId;

    public Answer(int questionId, String answer, boolean isRight) {
        this.questionId = questionId;
        this.answer = answer;
        this.isRight = isRight;
    }
}
