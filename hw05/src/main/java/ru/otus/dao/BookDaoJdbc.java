package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcTemplate jdbc;

    private final GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    @Override
    public long count() {
        return jdbc.queryForObject("select count(id) as count from book", Map.of(), Long.class);
    }

    @Override
    public long insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("genre_id", book.getGenre().getId());
        params.addValue("author_id", book.getAuthor().getId());
        jdbc.update("insert into book (title, genre_id, author_id) values(:title, :genre_id, :author_id)",
                params, generatedKeyHolder);
        return Objects.requireNonNull(generatedKeyHolder.getKey()).longValue();
    }

    @Override
    public Book getById(long id) {
        try {
            return jdbc.queryForObject(
                    "select b.id book_id, b.title book_title, b.genre_id book_genre_id, b.author_id book_author_id, " +
                            "a.title author_title, g.title genre_title " +
                            "from book b " +
                            "left join author a on a.id = b.author_id " +
                            "left join genre g on g.id = b.genre_id " +
                            "where b.id = :id",
                    Map.of("id", id), new BookMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(Book book) {
        jdbc.update("update book " +
                        "set title = :title, author_id = :author_id, genre_id = :genre_id " +
                        "where id = :id",
                Map.of("id", book.getId(), "title", book.getTitle(),
                        "author_id", book.getAuthor().getId(),
                        "genre_id", book.getGenre().getId()));
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                "select b.id book_id, b.title book_title, b.genre_id book_genre_id, b.author_id book_author_id, " +
                        "a.title author_title, g.title genre_title " +
                        "from book b " +
                        "left join author a on a.id = b.author_id " +
                        "left join genre g on g.id = b.genre_id",
                Map.of(), new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from book where id = :id", Map.of("id", id));
    }


    private static class BookMapper implements RowMapper<Book> {

        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("book_id");
            String title = rs.getString("book_title");
            Genre genre = new Genre(rs.getLong("book_genre_id"), rs.getString("genre_title"));
            Author author = new Author(rs.getLong("book_author_id"), rs.getString("author_title"));
            return new Book(id, title, genre, author);
        }
    }
}
