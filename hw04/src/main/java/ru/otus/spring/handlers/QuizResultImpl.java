package ru.otus.spring.handlers;

import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.Question;

import java.util.Map;

public class QuizResultImpl implements QuizResult {

    private final Person person;

    private final Map<Question, Answer> answers;

    private final int countRightAnswers;

    public QuizResultImpl(Person person, Map<Question, Answer> answers, int countRightAnswers) {
        this.person = person;
        this.answers = answers;
        this.countRightAnswers = countRightAnswers;
    }

    @Override
    public Person getPerson() {
        return this.person;
    }

    @Override
    public int getPersonRightsAnswers() {
        return (int) answers.entrySet().stream().filter(it -> it.getValue().isRight()).count();
    }

    @Override
    public boolean isPass() {
        return this.getPersonRightsAnswers() >= countRightAnswers;
    }

    @Override
    public int totalNumberQuestions() {
        return answers.size();
    }
}
