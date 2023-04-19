package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.RunnerService;


public class Main {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        RunnerService runnerService = context.getBean("runnerService", RunnerService.class);

        runnerService.run();

        context.close();
    }
}