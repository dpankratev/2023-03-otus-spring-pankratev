package ru.otus.domain;

import lombok.Data;


@Data
public class Author {

    private final long id;

    private final String title;

    public Author(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Author(String title) {
       this(-1, title);
    }
}
