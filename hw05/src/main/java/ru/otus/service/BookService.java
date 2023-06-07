package ru.otus.service;

import ru.otus.domain.Book;

import java.util.List;

public interface BookService {

    Book getById(long id);

    long create(Book book);

    void update(Book book);

    void delete(long id);

    List<Book> getAll();

    long getCount();

}
