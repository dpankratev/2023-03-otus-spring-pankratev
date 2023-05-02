package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    private final int numberQuestionsForTest;

    private final int rightAnswerToPass;

    public QuestionServiceImpl(QuestionDao dao,
                               @Value("${question.numberToAsk}") int numberQuestionsForTest,
                               @Value("${question.rightAnswerToPass}") int rightAnswerToPass) {
        this.dao = dao;
        this.numberQuestionsForTest = numberQuestionsForTest;
        this.rightAnswerToPass = rightAnswerToPass;
    }

    @Override
    public List<Question> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Question> questionsForTest() {

        List<Question> questionList = this.findAll();

        int toIndex = Math.max(questionList.size(), numberQuestionsForTest);

        return questionList.subList(0, toIndex);
    }

    @Override
    public int getNumberRightAnswerToPass() {
        return rightAnswerToPass;
    }
}
