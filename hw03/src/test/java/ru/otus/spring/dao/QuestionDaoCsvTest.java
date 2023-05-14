package ru.otus.spring.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.config.DaoConfig;
import ru.otus.spring.domain.Answer;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Тест QuestionDaoCsvTest")
class QuestionDaoCsvTest {

    private QuestionDao questionDao;

    @BeforeEach
    void setUp() {
        DaoConfig daoConfig = mock(AppProps.class);
        when(daoConfig.getFileName()).thenReturn("questionsTest.csv");
        questionDao = new QuestionDaoCsv(daoConfig);
    }

    @DisplayName("должен содержать количество вопросов, равное общем количеству")
    @Test
    void checkThatSizeOfAllQuestionsListIsRight() {
        assertThat(questionDao.findAll()).size().isEqualTo(5);
    }

    @DisplayName("содержит верный первый вопрос")
    @Test
    void checkThatFirstQuestionIsRight() {
        assertThat(questionDao.findAll().get(0))
                .matches(q -> "Question1".equals(q.getQuestion()))
                .matches(q -> "Q1_Answer1;Q1_Answer2;Q1_Answer3;Q1_Answer4;Q1_Answer5;Q1_Answer6"
                        .equals(q.getAnswers().stream().map(Answer::getAnswer).collect(Collectors.joining(";"))));
    }
}