package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.IncorrectNumberAnswer;
import ru.otus.spring.exception.QuestionsLoadingException;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.MessageService;
import ru.otus.spring.service.QuestionFormatterService;
import ru.otus.spring.service.QuestionService;
import ru.otus.spring.service.RunnerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RunnerServiceImpl implements RunnerService, CommandLineRunner {

    private final QuestionService questionService;

    private final QuestionFormatterService formattedOutput;

    private final IOService ioService;

    private final MessageService messageService;


    @Override
    public void runService() {

        List<Question> questionsForQuiz = null;
        Person person = askName();

        try {
            questionsForQuiz = questionService.findAllLimit();
        } catch (QuestionsLoadingException e) {
            String message = String.format("%s: %s", messageService.getLocalizedMessage("error.loading"), e);
            ioService.output(message);
        }

        Map<Question, Answer> personsAnswers = askQuestions(questionsForQuiz);

        var testResultForPrint = formattedOutput.getTestResult(personsAnswers, person);
        ioService.output(testResultForPrint);

    }

    @Override
    public void run(String... args) {
        this.runService();
    }

    private Person askName() {
        ioService.output(messageService.getLocalizedMessage("ask.name"));
        String personName = ioService.input();
        Person person = new Person(personName);
        return person;
    }

    private Map<Question, Answer> askQuestions(List<Question> questions) {
        Map<Question, Answer> personsAnswers = new HashMap<>();

        for (var question : questions) {
            var answers = question.getAnswers();

            ioService.output(formattedOutput.getQuestionForAsk(question));

            do {
                ioService.output(String.format("%s: ", messageService.getLocalizedMessage("print.your-answer")));
                String inputAnswer = ioService.input();
                try {
                    personsAnswers.put(question, this.getAnswer(question, inputAnswer));
                    break;
                } catch (IncorrectNumberAnswer e) {
                    ioService.output(e.getMessage());
                }
            } while (true);
        }

        return personsAnswers;
    }

    private Answer getAnswer(Question question, String inputAnswer) {
        String message = messageService.getLocalizedMessage("print.incorrect-input");
        List<Answer> answersList = question.getAnswers();
        int numberAnswer;

        try {
            numberAnswer = Integer.parseInt(inputAnswer);
        } catch (NumberFormatException e) {
            throw new IncorrectNumberAnswer(message, e);
        }

        if (numberAnswer < 1 || numberAnswer > answersList.size()) {
            throw new IncorrectNumberAnswer(message, null);
        }

        return answersList.get(--numberAnswer);
    }


}
