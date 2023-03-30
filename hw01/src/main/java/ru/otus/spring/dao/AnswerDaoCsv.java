package ru.otus.spring.dao;

import org.springframework.core.io.ClassPathResource;
import ru.otus.spring.domain.Answer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoCsv implements AnswerDao {

    private final BufferedReader br;

    public AnswerDaoCsv(String value) throws IOException {
        this.br = new BufferedReader(new InputStreamReader(new ClassPathResource(value).getInputStream()));
    }

    @Override
    public List<Answer> findByQuestionId(int id) throws IOException {
        var str = br.readLine();
        while (str != null) {
            int qId = Integer.parseInt(str.split(";")[0]);
            if (qId != id) {
                str = br.readLine();
                continue;
            }
            List<Answer> res = new ArrayList<>();
            String[] arr = str.split(";");
            for (int i = 2; i < arr.length; i++) {
                if (i == 2) {
                    res.add(new Answer(qId, arr[i], true));
                } else {
                    res.add(new Answer(qId, arr[i], false));
                }
            }
            return res;
        }
        return null;
    }
}
