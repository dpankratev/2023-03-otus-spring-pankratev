package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.QuestionsLoadingException;

import java.util.List;

public interface QuestionService {

    List<Question> findAll() throws QuestionsLoadingException;

}
