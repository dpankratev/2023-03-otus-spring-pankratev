package ru.otus.spring.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.dao.QuestionDaoCsv;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.QuestionsLoadingException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class QuestionServiceImplTest {


    @Test
    public void whenFindAllThenOk() throws QuestionsLoadingException {

        List<Question> expectedQuestions = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            int finalI = i;
            expectedQuestions.add(new Question(i, "Question" + i,
                    new LinkedHashMap<>() {{
                        put("Q" + finalI + "_Answer" + 1, true);
                        put("Q" + finalI + "_Answer" + 2, false);
                        put("Q" + finalI + "_Answer" + 3, false);
                        put("Q" + finalI + "_Answer" + 4, false);
                        put("Q" + finalI + "_Answer" + 5, false);
                        put("Q" + finalI + "_Answer" + 6, false);
                    }}));
        }

        QuestionDao questionDao = new QuestionDaoCsv("questionsTest.csv");
        List<Question> questions = questionDao.findAll();
        for (int i = 0; i < questions.size(); i++) {
            Assertions.assertEquals(expectedQuestions.get(i).toString(), questions.get(i).toString());
        }
    }
}
