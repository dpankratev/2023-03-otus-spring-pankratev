package ru.otus.spring.service;

import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.Question;

import java.util.List;
import java.util.Map;

public interface InteractiveService {

    Person askName();

    Map<Question, Answer> askQuestions(List<Question> questions);

}
