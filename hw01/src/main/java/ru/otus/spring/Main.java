package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.exception.QuestionsLoadingException;
import ru.otus.spring.service.RunnerServiceImpl;


public class Main {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        RunnerServiceImpl runnerService = context.getBean("runnerService", RunnerServiceImpl.class);

        try {
            runnerService.run();
        } catch (QuestionsLoadingException e) {
            System.out.println("Error during load questions.");
        }

        context.close();
    }
}