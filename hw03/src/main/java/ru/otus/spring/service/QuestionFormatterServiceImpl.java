package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.Question;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuestionFormatterServiceImpl implements QuestionFormatterService {


    private final AppProps props;

    private final MessageSource messageSource;


    @Override
    public String convertQuestionsListToString(List<Question> questions) {
        StringBuilder sb = new StringBuilder();
        for (Question question : questions) {

            sb.append(getLocalizedMessage("print.questions-list",
                    new String[]{String.valueOf(question.getId()), question.getQuestion()}));

            for (var answer : question.getAnswers()) {
                if (answer.isRight()) {
                    sb.append(getLocalizedMessage("word.correct",
                                    new String[]{String.valueOf(question.getId()), question.getQuestion()})).
                            append(": ");
                } else {
                    sb.append(getLocalizedMessage("word.incorrect",
                                    new String[]{String.valueOf(question.getId()), question.getQuestion()}))
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
                getLocalizedMessage("word.answer")));

        var index = 1;
        for (var answer : question.shuffledAnswer()) {
            sb.append(String.format("%x: %s\n", index++, answer.getAnswer()));
        }

        return sb.toString();
    }

    @Override
    public String getTestResult(Map<Question, Answer> personsAnswers, Person person) {
        var wordFail = getLocalizedMessage("word.fail" );
        var wordPass = getLocalizedMessage("word.pass");
        return getLocalizedMessage("print.result",
                new String[]{person.getUserName(),
                        String.valueOf(getNumberRightAnswer(personsAnswers)),
                        String.valueOf(personsAnswers.size()),
                        (getNumberRightAnswer(personsAnswers) >= props.getRightAnswerToPass() ? wordPass : wordFail)});
    }

    private Long getNumberRightAnswer(Map<Question, Answer> personTest) {
        return personTest.entrySet().stream().filter(it -> it.getValue().isRight()).count();
    }

    private String getLocalizedMessage(String code, String[] args) {
        return messageSource.getMessage(code, args, props.getLocale());
    }

    private String getLocalizedMessage(String code) {
        return this.getLocalizedMessage(code, null);
    }
}
