package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.QuestionsLoadingException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDaoCsv implements QuestionDao {

    private final String resourceFile;

    private final AppProps props;

    public QuestionDaoCsv(@Value("${question.file-prefix}") String resourceFile, AppProps props) {
        this.resourceFile = resourceFile + "_" + props.getLocale() + ".csv";
        this.props = props;
    }


    @Override
    public List<Question> findAll() {
        String line;
        List<Question> questions = new ArrayList<>();
        try (var is = new InputStreamReader(new ClassPathResource(resourceFile).getInputStream());
             var br = new BufferedReader(is)) {
            while ((line = br.readLine()) != null) {
                List<Answer> answers = new ArrayList<>();
                String[] lineArray = line.split(";");
                for (int i = 2; i < lineArray.length; i++) {
                    var answer = new Answer(lineArray[i], i == 2);
                    answers.add(answer);
                }
                questions.add(new Question(Integer.parseInt(lineArray[0]), lineArray[1], answers));
            }
        } catch (Exception e) {
            throw new QuestionsLoadingException(e);
        }
        return questions;
    }
}
