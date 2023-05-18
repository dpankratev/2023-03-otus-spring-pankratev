package ru.otus.spring.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.config.DaoConfig;
import ru.otus.spring.config.QuestionConfig;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.dao.QuestionDaoCsv;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Test QuestionServiceImplTest")
class QuestionServiceImplTest {

    private QuestionDao questionDao;

    private QuestionService questionService;

    private QuestionConfig questionConfig;


    @BeforeEach
    void setUp() {
        questionConfig = mock(AppProps.class);
        questionDao = mock(QuestionDao.class);
        questionService = new QuestionServiceImpl(questionDao, questionConfig);

    }

    @DisplayName("содержит количество вопросов, равное общему количеству")
    @Test
    void checkThatSizeOfAllQuestionsIsRight() {
        when(questionDao.findAll()).thenReturn(List.of(mock(Question.class), mock(Question.class)));
        assertThat(questionService.findAll()).size().isEqualTo(2);
    }

    @DisplayName("содержит количество вопросов для теста, равное общему количеству при большем количестве в настройках")
    @Test
    void checkThatSizeOfLimitQuestionsIsRight() {
        when(questionConfig.getNumberQuestionsForQuiz()).thenReturn(10);
        when(questionDao.findAll()).thenReturn(List.of(mock(Question.class), mock(Question.class), mock(Question.class)));
        assertThat(questionService.findAllLimit()).size().isEqualTo(3);
    }

}