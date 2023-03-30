package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {

    Question findById(int id) throws IOException;

    List<Question> findAll() throws IOException;
}
