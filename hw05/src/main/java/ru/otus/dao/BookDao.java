package ru.otus.dao;

import ru.otus.domain.Book;

import java.util.List;

public interface BookDao {

    long count();

    long insert(Book book);

    Book getById(long id);

    void update(Book book);

    void deleteById(long id);

    List<Book> getAll();


}
