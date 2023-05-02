package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.spring.config.ApplicationConfig;
import ru.otus.spring.service.RunnerService;



public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ApplicationConfig.class);

        context.refresh();

        RunnerService runnerService = context.getBean(RunnerService.class);

        runnerService.run();
    }
}