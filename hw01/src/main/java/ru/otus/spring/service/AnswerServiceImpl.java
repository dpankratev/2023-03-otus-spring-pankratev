package ru.otus.spring.service;

import ru.otus.spring.dao.AnswerDao;
import ru.otus.spring.domain.Answer;

import java.io.IOException;
import java.util.List;

public class AnswerServiceImpl implements AnswerService{

    private final AnswerDao dao;

    public AnswerServiceImpl(AnswerDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Answer> findByQuestionId(int id) throws IOException {
        return dao.findByQuestionId(id);
    }
}
