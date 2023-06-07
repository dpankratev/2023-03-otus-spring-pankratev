package ru.otus.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
class BookDaoJdbcTest {

    private static final long EXPECTED_BOOK_COUNT = 1;
    private static final long EXISTING_BOOK_ID = 1;

    private static final String EXISTING_BOOK_TITLE = "Book1-Test";
    private static final String CREATED_BOOK_TITLE = "CreatedBook";

    private static final long EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_AUTHOR_NAME = "Author1-Test";
    private static final long EXISTING_GENRE_ID = 1;
    private static final String EXISTING_GENRE_TITLE = "Genre1-Test";

    @Autowired
    BookDaoJdbc bookDao;

    @Test
    void shouldReturnExpectedCount() {
        long actualAuthorCount = bookDao.count();
        assertThat(actualAuthorCount).isEqualTo(EXPECTED_BOOK_COUNT);
    }

    @Test
    void shouldInsertBook() {
        var genre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_TITLE);
        var author = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);

        var addedBook = new Book(CREATED_BOOK_TITLE, genre, author);
        var bookId = bookDao.insert(addedBook);

        var expectedBook = new Book(bookId, CREATED_BOOK_TITLE, genre, author);

        var actualBook = bookDao.getById(bookId);

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);

    }

    @Test
    void shouldReturnExpectedBookById() {
        var genre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_TITLE);
        var author = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);

        var expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, genre, author);

        var actualBook = bookDao.getById(EXISTING_BOOK_ID);

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void shouldCorrectDeleteAuthorById() {
        assertThat(bookDao.getById(EXISTING_BOOK_ID)).isNotNull();

        bookDao.deleteById(EXISTING_BOOK_ID);

        assertThat(bookDao.getById(EXISTING_AUTHOR_ID)).isNull();
    }

    @Test
    void shouldReturnExpectedBooksList() {
        var genre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_TITLE);
        var author = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);

        var expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, genre, author);
        List<Book> actualBookList = bookDao.getAll();
        assertThat(actualBookList)
                .containsExactlyInAnyOrder(expectedBook);
    }
}