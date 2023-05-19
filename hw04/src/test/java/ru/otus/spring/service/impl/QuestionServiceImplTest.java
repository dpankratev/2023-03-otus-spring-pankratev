package ru.otus.spring.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.service.QuestionService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Тест QuestionServiceImplTest")
@SpringBootTest
class QuestionServiceImplTest {

    @Autowired
    QuestionService questionService;

    @DisplayName("должен правильное значение количества всех вопросов")
    @Test
    void shouldContainsCorrectSizeAllQuestions() {
        assertThat(questionService.findAll().size())
                .isEqualTo(5);
    }

    @DisplayName("должен содержать правильное количество вопросов для теста")
    @Test
    void shouldContainsCorrectNumberLimitQuestions() {
        assertThat(questionService.findAllLimit().size())
                .isEqualTo(3);
    }
}