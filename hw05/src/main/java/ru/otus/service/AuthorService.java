package ru.otus.service;

import ru.otus.domain.Author;

import java.util.List;

public interface AuthorService {

    Author getById(long id);

    long create(Author author);

    void update(Author author);

    void delete(long id);

    List<Author> getAll();

    long getCount();

}
