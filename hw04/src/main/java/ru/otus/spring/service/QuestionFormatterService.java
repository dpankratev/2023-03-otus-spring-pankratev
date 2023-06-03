package ru.otus.spring.service;

import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.Question;
import ru.otus.spring.handlers.QuizResult;

import java.util.List;

public interface QuestionFormatterService {

    String convertQuestionsListToString(List<Question> questions);

    String getQuestionForAsk(Question question);

    String getTestResult(Person person, QuizResult quizResult);

}
