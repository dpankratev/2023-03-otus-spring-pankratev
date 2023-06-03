package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.AuthorDao;
import ru.otus.domain.Author;
import ru.otus.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;

    @Override
    public Author getById(long id) {
        return dao.getById(id);
    }

    @Override
    public long create(Author author) {
        return dao.insert(author);
    }

    @Override
    public void update(Author author) {
        dao.update(author);
    }

    @Override
    public void delete(long id) {
        dao.deleteById(id);
    }

    @Override
    public List<Author> getAll() {
        return dao.getAll();
    }

    @Override
    public long getCount() {
        return dao.count();
    }
}
