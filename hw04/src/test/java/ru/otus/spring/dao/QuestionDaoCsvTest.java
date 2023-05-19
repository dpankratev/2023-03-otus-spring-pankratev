package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.domain.Answer;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест QuestionDaoCsvTest")
@SpringBootTest
class QuestionDaoCsvTest {

    @Autowired
    private QuestionDao questionDao;

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
