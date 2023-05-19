package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.QuestionConfig;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    private final QuestionConfig questionConfig;


    @Override
    public List<Question> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Question> findAllLimit() {

        List<Question> questionList = this.findAll();

        int toIndex = Math.min(questionList.size(), questionConfig.getNumberQuestionsForQuiz());

        return questionList.subList(0, toIndex);
    }

}
