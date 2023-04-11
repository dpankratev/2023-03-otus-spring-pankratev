package ru.otus.spring.service;

import ru.otus.spring.exception.QuestionsLoadingException;

public interface RunnerService {

    void run() throws QuestionsLoadingException;

}
