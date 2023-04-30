package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.QuestionsLoadingException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class QuestionDaoCsvTest {


    @Test
    public void whenFindAllThenTextAllQuestionsAndAnswersIsCorrect() {

        List<Question> expectedQuestions = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            int finalI = i;
            expectedQuestions.add(new Question(i, "Question" + i,
                    new ArrayList<>() {{
                        add(new Answer("Q" + finalI + "_Answer" + 1, true));
                        add(new Answer("Q" + finalI + "_Answer" + 2, false));
                        add(new Answer("Q" + finalI + "_Answer" + 3, false));
                        add(new Answer("Q" + finalI + "_Answer" + 4, false));
                        add(new Answer("Q" + finalI + "_Answer" + 5, false));
                        add(new Answer("Q" + finalI + "_Answer" + 6, false));
                    }}));
        }


        QuestionDao questionDao = new QuestionDaoCsv("questionsTest.csv");
        List<Question> questions = questionDao.findAll();

        assertThat(questions)
                .isNotEmpty()
                .hasSize(5)
                .extracting(Question::getQuestion)
                .containsAll(expectedQuestions.stream().map(Question::getQuestion).collect(Collectors.toList()));

        assertThat(questions)
                .map(question -> question.getAnswers().stream())
                .map(answerStream -> answerStream.map(Answer::getAnswer))
                .map(s -> s.collect(Collectors.toList()))
                .containsAll(expectedQuestions.stream().map(question -> question.getAnswers().stream())
                        .map(answerStream -> answerStream.map(Answer::getAnswer))
                        .map(s -> s.collect(Collectors.toList())).collect(Collectors.toList()));

    }

    @Test
    public void whenFileNotExistsThenQuestionsLoadingException() {
        QuestionDao questionDao = new QuestionDaoCsv("notExists.file");

        assertThatExceptionOfType(QuestionsLoadingException.class).isThrownBy(questionDao::findAll);
    }

}