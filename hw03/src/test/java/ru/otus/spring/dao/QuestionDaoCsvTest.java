package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.service.RunnerService;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QuestionDaoCsvTest {

    @Autowired
    private QuestionDaoCsv questionDaoCsv;

    @MockBean
    private RunnerService runnerService;


    @Test
    void checkThatSizeOfAllQuestionsListIsRight() {
        assertThat(questionDaoCsv.findAll()).size().isEqualTo(5);
    }

    @Test
    void checkThatFirstQuestionIsRight(){
        assertThat(questionDaoCsv.findAll().get(0))
                .matches(q-> "Question1".equals(q.getQuestion()))
                .matches(q -> "Q1_Answer1;Q1_Answer2;Q1_Answer3;Q1_Answer4;Q1_Answer5;Q1_Answer6"
                        .equals(q.getAnswers().stream().map(Answer::getAnswer).collect(Collectors.joining(";"))));
    }
}