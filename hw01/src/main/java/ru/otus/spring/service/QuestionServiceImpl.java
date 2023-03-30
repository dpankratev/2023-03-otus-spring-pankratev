package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public Question findById(int id) throws IOException {
        return dao.findById(id);
    }

    @Override
    public List<Question> findAll() throws IOException {
        return dao.findAll();
    }
}
