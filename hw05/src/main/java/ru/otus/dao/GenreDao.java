package ru.otus.dao;

import ru.otus.domain.Genre;

import java.util.List;

public interface GenreDao {

    long count();

    long insert(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);

    void update(Genre genre);

}
