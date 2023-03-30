package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionService {

    Question findById(int id) throws IOException;

    List<Question> findAll() throws IOException;

}
