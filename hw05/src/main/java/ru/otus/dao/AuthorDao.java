package ru.otus.dao;

import ru.otus.domain.Author;

import java.util.List;

public interface AuthorDao {

    long count();

    long insert(Author author);

    Author getById(long id);

    void update(Author author);

    List<Author> getAll();

    void deleteById(long id);
}
