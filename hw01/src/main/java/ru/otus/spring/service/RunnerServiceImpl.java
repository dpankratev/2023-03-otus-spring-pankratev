package ru.otus.spring.service;

import ru.otus.spring.exception.QuestionsLoadingException;

import java.nio.charset.StandardCharsets;

public class RunnerServiceImpl implements RunnerService {


    private final QuestionService questionService;

    private final IOService ioService;

    private final FormattedOutput formattedOutput;

    public RunnerServiceImpl(QuestionService questionService, IOService ioService, FormattedOutput formattedOutput) {
        this.questionService = questionService;
        this.ioService = ioService;
        this.formattedOutput = formattedOutput;
    }

    @Override
    public void run() throws QuestionsLoadingException {
        ioService.output(formattedOutput.getAllQuestionsWithAnswer(questionService.findAll())
                .getBytes(StandardCharsets.UTF_8));
    }
}
