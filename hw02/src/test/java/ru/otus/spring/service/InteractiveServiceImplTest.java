package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.dao.QuestionDaoCsv;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


class InteractiveServiceImplTest {

    IOService ioService;

    QuestionFormatterService formatterService;

    InteractiveService interactiveService;

    QuestionDao questionDao;

    @BeforeEach
    void setUp() {
        String resourceFile = "questionsTest.csv";
        ioService = Mockito.mock(IOService.class);
        formatterService = Mockito.mock(QuestionFormatterService.class);
        interactiveService = new InteractiveServiceImpl(ioService, formatterService);
        questionDao = new QuestionDaoCsv(resourceFile);
    }

    @Test
    void askPersonName() {

        String name = "Username";
        Mockito.when(ioService.input()).thenReturn("Username");

        assertThat(interactiveService.askName().getUserName()).isEqualTo(name);
    }

    @Test
    void whenAskQuestionsAndAllAnswerIsRight() {

        List<Question> questionList = questionDao.findAll();

        Mockito.when(ioService.input()).thenReturn("1");

        var rst = interactiveService.askQuestions(questionList);
        assertThat(rst)
                .hasSize(5)
                .extracting(Map::values)
                .extracting(list -> list.stream().allMatch(Answer::isRight))
                .isEqualTo(true);
    }

    @Test
    void whenAskQuestionsAndAllAnswerIsWrong() {

        List<Question> questionList = questionDao.findAll();

        Random random = new Random();
        String input = String.valueOf((random.nextInt(6 - 2) + 2));
        Mockito.when(ioService.input()).thenReturn(input);

        var rst = interactiveService.askQuestions(questionList);
        assertThat(rst)
                .hasSize(5)
                .extracting(Map::values)
                .extracting(list -> list.stream().allMatch(Answer::isRight))
                .isEqualTo(false);
    }
}