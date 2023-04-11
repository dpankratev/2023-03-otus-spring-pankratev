package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.List;

public class FormattedOutputImpl implements FormattedOutput {


    @Override
    public String getAllQuestionsWithAnswer(List<Question> questions) {
        StringBuilder sb = new StringBuilder();
        for (Question question : questions) {
            sb.append("id = ")
                    .append(question.getId())
                    .append(". Question: ")
                    .append(question.getQuestion())
                    .append("\n")
                    .append("Answers:\n");
            for (var answer : question.getAnswers().entrySet()) {
                if (answer.getValue()) {
                    sb.append("Correct: ");
                } else {
                    sb.append("Incorrect: ");
                }
                sb.append(answer.getKey())
                        .append("\n");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
