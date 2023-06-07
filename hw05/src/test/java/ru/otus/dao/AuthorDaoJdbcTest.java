package ru.otus.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import({AuthorDaoJdbc.class, BookDaoJdbc.class})
class AuthorDaoJdbcTest {

    private static final long EXPECTED_AUTHOR_COUNT = 1;
    private static final long EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_AUTHOR_NAME = "Author1-Test";

    private static final long EXISTING_BOOK_ID = 1;

    @Autowired
    private AuthorDaoJdbc authorDao;

    @Autowired
    private BookDaoJdbc bookDao;

    @Test
    void shouldReturnExpectedCount() {
        long actualAuthorCount = authorDao.count();
        assertThat(actualAuthorCount).isEqualTo(EXPECTED_AUTHOR_COUNT);
    }

    @Test
    void shouldInsertAuthor() {

        var authorTitle = "Added Author";
        var authorId = authorDao.insert(new Author(authorTitle));
        var expectedAuthor = new Author(authorId, authorTitle);
        Author actualAuthor = authorDao.getById(authorId);

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);

    }

    @Test
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        Author actualAuthor = authorDao.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void shouldCorrectDeleteAuthorById() {
        assertThat(authorDao.getById(EXISTING_AUTHOR_ID)).isNotNull();

        bookDao.deleteById(EXISTING_BOOK_ID);
        authorDao.deleteById(EXISTING_AUTHOR_ID);

        assertThat(authorDao.getById(EXISTING_AUTHOR_ID)).isNull();
    }


    @Test
    void shouldReturnExpectedAuthorsList() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        List<Author> actualAuthorsList = authorDao.getAll();
        assertThat(actualAuthorsList)
                .containsExactlyInAnyOrder(expectedAuthor);
    }


}