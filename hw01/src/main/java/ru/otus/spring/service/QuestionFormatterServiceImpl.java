package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.List;

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
}
