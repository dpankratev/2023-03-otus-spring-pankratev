package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.config.AppProps;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest()
class QuestionServiceImplTest {

    @Autowired
    private AppProps props;

    @Autowired
    private QuestionService questionService;

    @MockBean
    private RunnerService runnerService;

    @Test
    void checkThatSizeOfAllQuestionsIsRight() {
        assertThat(questionService.findAll()).size().isEqualTo(5);
    }

    @Test
    void checkThatSizeOfLimitQuestionsIsRight() {
        assertThat(questionService.findAllLimit()).size().isEqualTo(3);
    }

}