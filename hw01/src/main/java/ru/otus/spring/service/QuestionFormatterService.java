package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.List;

public interface QuestionFormatterService {

    String convertQuestionsListToString(List<Question> questions);

}
