package ru.otus.spring.dao;

import ru.otus.spring.domain.Answer;

import java.io.IOException;
import java.util.List;

public interface AnswerDao {

    List<Answer> findByQuestionId(int id) throws IOException;
}
