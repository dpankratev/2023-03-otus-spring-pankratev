package ru.otus.domain;

import lombok.Data;


@Data
public class Book {

    private final long id;

    private final String title;

    private final Genre genre;

    private final Author author;

    public Book(long id, String title, Genre genre, Author author) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.author = author;
    }

    public Book(String title, Genre genre, Author author) {
       this(-1, title,genre, author);
    }

}
