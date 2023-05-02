package ru.otus.spring.domain;

import lombok.Getter;

@Getter
public class Person {

    private final String userName;

    public Person(String userName) {
        this.userName = userName;
    }
}
