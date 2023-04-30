package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.Question;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuestionFormatterServiceImpl implements QuestionFormatterService {


    @Override
    public String convertQuestionsListToString(List<Question> questions) {
        StringBuilder sb = new StringBuilder();
        for (Question question : questions) {
            sb.append("id = ")
                    .append(question.getId())
                    .append(". Question: ")
                    .append(question.getQuestion())
                    .append("\n")
                    .append("Answers:\n");
            for (var answer : question.getAnswers()) {
                if (answer.isRight()) {
                    sb.append("Correct: ");
                } else {
                    sb.append("Incorrect: ");
                }
                sb.append(answer.getAnswer())
                        .append("\n");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public String getQuestionForAsk(Question question) {
        StringBuilder sb = new StringBuilder();
        sb.append(question.getQuestion());
        sb.append("\nAnswers:\n");
        AtomicInteger count = new AtomicInteger(1);
        question.shuffledAnswer()
                .forEach(answer -> sb.append(count.getAndIncrement())
                        .append(": ")
                        .append(answer.getAnswer()).append("\n"));

        return sb.toString();
    }

    @Override
    public String getTestResult(Map<Question, Answer> personsAnswers, Person person, int numberToPass) {
        return String.format("%s, the result of your test: %s right of %s. %s.",
                person.getUserName(),
                getNumberRightAnswer(personsAnswers),
                personsAnswers.size(),
                (getNumberRightAnswer(personsAnswers) >= numberToPass ?
                        "Pass" : "Fail"));
    }

    private Long getNumberRightAnswer(Map<Question, Answer> personTest) {
        return personTest.entrySet().stream().filter(it -> it.getValue().isRight()).count();
    }
}
