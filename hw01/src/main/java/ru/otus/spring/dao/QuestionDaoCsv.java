package ru.otus.spring.dao;

import org.springframework.core.io.ClassPathResource;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.QuestionsLoadingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class QuestionDaoCsv implements QuestionDao {

    private final String value;

    public QuestionDaoCsv(String value) {
        this.value = value;
    }


    @Override
    public List<Question> findAll() throws QuestionsLoadingException{
        String line;
        List<Question> questions = new ArrayList<>();
        try (var is = new InputStreamReader(new ClassPathResource(value).getInputStream());
             var br = new BufferedReader(is)) {
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split(";");
                Map<String, Boolean> answers = new LinkedHashMap<>();
                for (int i = 2; i < lineArray.length; i++) {
                    answers.put(lineArray[i], i == 2);
                }
                questions.add(new Question(Integer.parseInt(lineArray[0]), lineArray[1], answers));
            }
        } catch (IOException e) {
            throw new QuestionsLoadingException(e);
        }
        return questions;
    }
}
