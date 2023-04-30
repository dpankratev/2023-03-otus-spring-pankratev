package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.List;

public interface QuestionService {

    List<Question> findAll();

    List<Question> questionsForTest();

    int getNumberRightAnswerToPass();

}