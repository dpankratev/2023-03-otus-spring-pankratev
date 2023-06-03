package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcTemplate jdbc;

    private final GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    @Override
    public long count() {
        Integer count = jdbc.queryForObject("select count(*) from author", Map.of(), Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public long insert(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", author.getTitle());
        jdbc.update("insert into author (title) values(:title)", params, generatedKeyHolder);
        return Objects.requireNonNull(generatedKeyHolder.getKey()).longValue();
    }

    @Override
    public Author getById(long id) {
        try {
            return jdbc.queryForObject(
                    "select id, title from author where id = :id", Map.of("id", id), new AuthorMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(Author author) {
        jdbc.update("update author set title :title where id = :id",
                Map.of("id", author.getId(), "title", author.getTitle()));
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select id, title from author", Map.of(), new AuthorMapper());
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from author where id = :id", Map.of("id", id));
    }

    private static class AuthorMapper implements RowMapper<Author> {
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String title = rs.getString("title");

            return new Author(id, title);
        }
    }

}
