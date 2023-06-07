package ru.otus.service;

import ru.otus.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre getById(long id);

    long create(Genre genre);

    void update(Genre genre);

    void delete(long id);

    List<Genre> getAll();

    long getCount();
}
