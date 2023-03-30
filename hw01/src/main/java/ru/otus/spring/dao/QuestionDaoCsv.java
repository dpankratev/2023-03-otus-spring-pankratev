package ru.otus.spring.dao;

import org.springframework.core.io.ClassPathResource;
import ru.otus.spring.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionDaoCsv implements QuestionDao {

    private final BufferedReader br;

    public QuestionDaoCsv(String value) throws IOException {
        this.br = new BufferedReader(new InputStreamReader(new ClassPathResource(value).getInputStream()));
    }

    @Override
    public Question findById(int id) throws IOException {
        var str = br.readLine();
        while (str != null) {
            int qId = Integer.parseInt(str.split(";")[0]);
            if (id == qId) {
                return new Question(qId, str.split(";")[1]);
            }
            str = br.readLine();
        }
        return null;
    }

    @Override
    public List<Question> findAll() {
        return br.lines()
                .map(line -> new Question(Integer.parseInt(line.split(";")[0]), line.split(";")[1]))
                .collect(Collectors.toList());
    }
}
