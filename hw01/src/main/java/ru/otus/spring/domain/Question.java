package ru.otus.spring.domain;

import lombok.Getter;

import java.util.Map;

public class Question {

    @Getter
    private final int id;

    @Getter
    private final String question;

    @Getter
    private final Map<String, Boolean> answers;

    public Question(int id, String question, Map<String, Boolean> answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answers=" + answers +
                '}';
    }
}
