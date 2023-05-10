package ru.otus.spring.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class AppProps {

    private Locale locale;

    private String numberQuestionsForQuiz;

    private String rightAnswerToPass;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public int getNumberQuestionsForQuiz() {
        return Integer.parseInt(numberQuestionsForQuiz);
    }

    public void setNumberQuestionsForQuiz(String numberQuestionsForQuiz) {
        this.numberQuestionsForQuiz = numberQuestionsForQuiz;
    }

    public int getRightAnswerToPass() {
        return Integer.parseInt(rightAnswerToPass);
    }

    public void setRightAnswerToPass(String rightAnswerToPass) {
        this.rightAnswerToPass = rightAnswerToPass;
    }
}
