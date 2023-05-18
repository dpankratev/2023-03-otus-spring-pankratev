package ru.otus.spring.service;

import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.Question;

import java.util.List;
import java.util.Map;

public interface QuestionFormatterService {

    String convertQuestionsListToString(List<Question> questions);

    String getQuestionForAsk(Question question);

    String getTestResult(Map<Question, Answer> personsAnswers, Person person);

}
