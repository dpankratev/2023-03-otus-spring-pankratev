package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.IncorrectNumberAnswer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InteractiveServiceImpl implements InteractiveService {

    private final IOService ioService;

    private final QuestionFormatterService formattedOutput;

    public InteractiveServiceImpl(IOService ioService, QuestionFormatterService formattedOutput) {
        this.ioService = ioService;
        this.formattedOutput = formattedOutput;
    }

    @Override
    public Person askName() {
        ioService.output("Enter your name:");
        String personName = ioService.input();
        Person person = new Person(personName);
        return person;
    }

    @Override
    public Map<Question, Answer> askQuestions(List<Question> questions) {
        Map<Question, Answer> personsAnswers = new HashMap<>();

        for (var question : questions) {
            var answers = question.getAnswers();

            ioService.output(formattedOutput.getQuestionForAsk(question));

            do {
                ioService.output("Your answer: ");
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
        String message = "Incorrect input.";
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
