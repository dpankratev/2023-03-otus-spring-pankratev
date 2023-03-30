package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.AnswerService;
import ru.otus.spring.service.QuestionService;

import java.io.IOException;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        QuestionService questionService = context.getBean(QuestionService.class);
        AnswerService answerService = context.getBean(AnswerService.class);

        questionService.findAll().forEach(q -> {
            System.out.println(q.getQuestion());
            try {
                var answers = answerService.findByQuestionId(q.getId());
                Collections.shuffle(answers);
                answers.forEach(a ->
                        System.out.println(a.getAnswer() + " : " + a.isRight()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("==================");
        });
        context.close();
    }
}