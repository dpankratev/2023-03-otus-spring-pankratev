package ru.otus.spring.service;

import ru.otus.spring.domain.Answer;

import java.io.IOException;
import java.util.List;

public interface AnswerService {

    List<Answer> findByQuestionId(int id) throws IOException;

}
