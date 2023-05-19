package ru.otus.spring.handlers;

import ru.otus.spring.domain.Person;

public interface QuizResult {

    Person getPerson();

    int totalNumberQuestions();

    int getPersonRightsAnswers();

    boolean isPass();

}
