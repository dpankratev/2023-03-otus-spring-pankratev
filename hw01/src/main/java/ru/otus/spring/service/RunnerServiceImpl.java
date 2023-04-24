package ru.otus.spring.service;

import ru.otus.spring.exception.QuestionsLoadingException;

public class RunnerServiceImpl implements RunnerService {


    private final QuestionService questionService;

    private final IOService ioService;

    private final QuestionFormatterService formattedOutput;

    public RunnerServiceImpl(QuestionService questionService, IOService ioService,
                             QuestionFormatterService formattedOutput) {
        this.questionService = questionService;
        this.ioService = ioService;
        this.formattedOutput = formattedOutput;
    }

    @Override
    public void run() {
        try {
            var allQuestions = questionService.findAll();
            String message = formattedOutput.convertQuestionsListToString(allQuestions);
            ioService.output(message);

        } catch (QuestionsLoadingException e) {
            String message = "Error during loading questions: " + e;
            ioService.output(message);
        }

    }
}
