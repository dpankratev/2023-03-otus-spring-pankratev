package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.BookDao;
import ru.otus.domain.Book;
import ru.otus.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao dao;

    @Override
    public Book getById(long id) {
        return dao.getById(id);
    }

    @Override
    public long create(Book book) {
        return dao.insert(book);
    }

    @Override
    public void update(Book book) {
        dao.update(book);
    }

    @Override
    public void delete(long id) {
        dao.deleteById(id);
    }

    @Override
    public List<Book> getAll() {
        return dao.getAll();
    }

    @Override
    public long getCount() {
        return dao.count();
    }


}
