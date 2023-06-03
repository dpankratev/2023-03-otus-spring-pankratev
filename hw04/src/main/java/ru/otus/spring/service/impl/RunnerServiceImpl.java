package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.QuestionConfig;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.IncorrectNumberAnswer;
import ru.otus.spring.exception.QuestionsLoadingException;
import ru.otus.spring.handlers.QuizResult;
import ru.otus.spring.handlers.QuizResultImpl;
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
public class RunnerServiceImpl implements RunnerService {

    private final QuestionService questionService;

    private final QuestionFormatterService formattedOutput;

    private final IOService ioService;

    private final MessageService messageService;

    private final QuestionConfig questionConfig;


    @Override
    public QuizResult startQuiz(Person person) {

        List<Question> questionsForQuiz = null;

        try {
            questionsForQuiz = questionService.findAllLimit();
        } catch (QuestionsLoadingException e) {
            String message = String.format("%s: %s", messageService.getLocalizedMessage("error.loading"), e);
            ioService.output(message);
        }

        QuizResult quizResult = askQuestions(person, questionsForQuiz);

        return quizResult;
    }

    private QuizResult askQuestions(Person person, List<Question> questions) {
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

        return new QuizResultImpl(person, personsAnswers, questionConfig.getRightAnswerToPass());
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
