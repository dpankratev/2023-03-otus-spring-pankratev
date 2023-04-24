package ru.otus.spring.domain;

import lombok.Getter;

import java.util.List;

public class Question {

    @Getter
    private final int id;

    @Getter
    private final String question;

    @Getter
    private final List<Answer> answers;

    public Question(int id, String question, List<Answer>answers ) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }
}


