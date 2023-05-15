package ru.otus.spring.service;

public interface MessageService {

    String getLocalizedMessage(String code, String... args);

}
