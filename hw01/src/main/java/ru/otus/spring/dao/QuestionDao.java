package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.QuestionsLoadingException;

import java.util.List;

public interface QuestionDao {

    List<Question> findAll() throws QuestionsLoadingException;
}
