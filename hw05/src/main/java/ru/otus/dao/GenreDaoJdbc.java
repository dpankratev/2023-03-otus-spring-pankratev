package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcTemplate jdbc;

    private final GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    @Override
    public long count() {
        Long count = jdbc.queryForObject("select count(id) from genre", Map.of(), Long.class);

        return count == null ? 0 : count;
    }

    @Override
    public long insert(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", genre.getTitle());
        jdbc.update("insert into genre (title) values(:title)", params, generatedKeyHolder);
        return Objects.requireNonNull(generatedKeyHolder.getKey()).longValue();
    }

    @Override
    public Genre getById(long id) {
        try {
            return jdbc.queryForObject("select id, title from genre where id = :id",
                    Map.of("id", id), new GenreMapper());
        } catch (
                EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Genre> getAll() {

        return jdbc.query("select id, title from genre", Map.of(), new GenreMapper());
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from genre where id = :id", Map.of("id", id));
    }

    @Override
    public void update(Genre genre) {
        jdbc.update("update genre set title :title where id = :id",
                Map.of("id", genre.getId(), "title", genre.getTitle()));
    }

    private static class GenreMapper implements RowMapper<Genre> {
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String title = rs.getString("title");

            return new Genre(id, title);
        }
    }
}
