package ru.otus.spring.service;

import ru.otus.spring.domain.Person;
import ru.otus.spring.handlers.QuizResult;

public interface RunnerService {

    QuizResult startQuiz(Person person);

}
