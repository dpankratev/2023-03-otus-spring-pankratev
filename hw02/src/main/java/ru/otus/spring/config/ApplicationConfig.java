package ru.otus.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.otus.spring")
public class ApplicationConfig {
}
