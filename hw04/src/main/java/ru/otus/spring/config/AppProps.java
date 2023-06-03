package ru.otus.spring.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class AppProps implements DaoConfig, MessageConfig, QuestionConfig {

    private Locale locale;

    private String numberQuestionsForQuiz;

    private String rightAnswerToPass;

    private String filePrefix;

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setNumberQuestionsForQuiz(String numberQuestionsForQuiz) {
        this.numberQuestionsForQuiz = numberQuestionsForQuiz;
    }

    public void setFilePrefix(String filePrefix) {
        this.filePrefix = filePrefix;
    }


    public void setRightAnswerToPass(String rightAnswerToPass) {
        this.rightAnswerToPass = rightAnswerToPass;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public String getFileName() {
        return filePrefix + "_" + locale + ".csv";
    }

    @Override
    public int getNumberQuestionsForQuiz() {
        return Integer.parseInt(numberQuestionsForQuiz);
    }

    @Override
    public int getRightAnswerToPass() {
        return Integer.parseInt(rightAnswerToPass);
    }

}
