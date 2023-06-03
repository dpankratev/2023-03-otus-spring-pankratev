package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.GenreDaoJdbc;
import ru.otus.domain.Genre;
import ru.otus.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDaoJdbc dao;

    @Override
    public Genre getById(long id) {
        return dao.getById(id);
    }

    @Override
    public long create(Genre genre) {
        return dao.insert(genre);
    }

    @Override
    public void update(Genre genre) {
        dao.update(genre);
    }

    @Override
    public void delete(long id) {
        dao.deleteById(id);
    }

    @Override
    public List<Genre> getAll() {
        return dao.getAll();
    }

    @Override
    public long getCount() {
        return dao.count();
    }
}
