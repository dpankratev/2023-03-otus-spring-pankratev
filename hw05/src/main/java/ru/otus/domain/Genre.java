package ru.otus.domain;

import lombok.Data;

@Data
public class Genre {

    private final long id;

    private final String title;

    public Genre(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Genre(String title) {
        this(-1, title);
    }
}
