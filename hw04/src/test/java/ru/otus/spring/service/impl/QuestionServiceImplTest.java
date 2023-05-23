package ru.otus.spring.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.config.MessageConfig;
import ru.otus.spring.config.QuestionConfig;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Тест QuestionServiceImplTest")
@MockBean(value = {MessageConfig.class})
@SpringBootTest
class QuestionServiceImplTest {

    @Autowired
    private QuestionService questionService;

    @MockBean
    private QuestionDao questionDao;

    @MockBean
    private QuestionConfig questionConfig;

    @DisplayName("должен правильное значение количества всех вопросов")
    @Test
    void shouldContainsCorrectSizeAllQuestions() {
        Mockito.when(questionDao.findAll())
                .thenReturn(IntStream.range(1,6)
                        .mapToObj(i -> new Question(i, String.valueOf(i), List.of()))
                        .collect(Collectors.toList()));

        assertThat(questionService.findAll().size())
                .isEqualTo(5);
    }

    @DisplayName("должен содержать правильное количество вопросов для теста")
    @Test
    void shouldContainsCorrectNumberLimitQuestions() {
        Mockito.when(questionDao.findAll())
                .thenReturn(IntStream.range(1,6)
                        .mapToObj(i -> new Question(i, String.valueOf(i), List.of()))
                        .collect(Collectors.toList()));

        Mockito.when(questionConfig.getNumberQuestionsForQuiz()).thenReturn(3);

        assertThat(questionService.findAllLimit().size())
                .isEqualTo(3);
    }
}