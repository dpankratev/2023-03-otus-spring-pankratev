package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.Question;
import ru.otus.spring.handlers.QuizResult;
import ru.otus.spring.service.MessageService;
import ru.otus.spring.service.QuestionFormatterService;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuestionFormatterServiceImpl implements QuestionFormatterService {

    private final MessageService messageService;


    @Override
    public String convertQuestionsListToString(List<Question> questions) {
        StringBuilder sb = new StringBuilder();
        for (Question question : questions) {

            sb.append(messageService.getLocalizedMessage("print.questions-list",
                    String.valueOf(question.getId()), question.getQuestion()));

            for (var answer : question.getAnswers()) {
                if (answer.isRight()) {
                    sb.append(messageService.getLocalizedMessage("word.correct",
                                    String.valueOf(question.getId()), question.getQuestion())).
                            append(": ");
                } else {
                    sb.append(messageService.getLocalizedMessage("word.incorrect",
                                    String.valueOf(question.getId()), question.getQuestion()))
                            .append(": ");
                }
                sb.append(answer.getAnswer()).append("\n");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public String getQuestionForAsk(Question question) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s\n%s\n",
                question.getQuestion(),
                messageService.getLocalizedMessage("word.answer")));

        var index = 1;
        for (var answer : question.shuffledAnswer()) {
            sb.append(String.format("%x: %s\n", index++, answer.getAnswer()));
        }

        return sb.toString();
    }

    @Override
    public String getTestResult(Person person, QuizResult quizResult) {
        var wordFail = messageService.getLocalizedMessage("word.fail");
        var wordPass = messageService.getLocalizedMessage("word.pass");
        return messageService.getLocalizedMessage("print.result",
                quizResult.getPerson().getUserName(),
                String.valueOf(quizResult.getPersonRightsAnswers()),
                String.valueOf(quizResult.totalNumberQuestions()),
                (quizResult.isPass() ? wordPass : wordFail));
    }

    private Long getNumberRightAnswer(Map<Question, Answer> personTest) {
        return personTest.entrySet().stream().filter(it -> it.getValue().isRight()).count();
    }


}
