package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.QuestionsLoadingException;

import java.util.List;
import java.util.Map;

@Service
public class RunnerServiceImpl implements RunnerService {

    private final QuestionService questionService;

    private final QuestionFormatterService formattedOutput;

    private final InteractiveService interactiveService;

    private IOService ioService;

    public RunnerServiceImpl(QuestionService questionService, InteractiveService interactiveService,
                             QuestionFormatterService formattedOutput, IOService ioService) {
        this.questionService = questionService;
        this.interactiveService = interactiveService;
        this.formattedOutput = formattedOutput;
        this.ioService = ioService;
    }

    @Override
    public void run() {

        List<Question> questionsForTest = null;
        Person person = interactiveService.askName();

        try {
            questionsForTest = questionService.questionsForTest();
        } catch (QuestionsLoadingException e) {
            String message = "Error during loading questions: " + e;
            ioService.output(message);
        }

        Map<Question, Answer> personsAnswers = interactiveService.askQuestions(questionsForTest);

        var testResultForPrint = formattedOutput.getTestResult(personsAnswers, person,
                questionService.getNumberRightAnswerToPass());
        ioService.output(testResultForPrint);

    }








}
