package ru.otus.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.service.BookService;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;


@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@SpringBootTest
class BookServiceImplTest {

    private static final long EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_AUTHOR_NAME = "Author1-Test";
    private static final long EXISTING_GENRE_ID = 1;
    private static final String EXISTING_GENRE_TITLE = "Genre1-Test";
    private static final long EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_TITLE = "Book1-Test";
    private static final String CREATED_BOOK_TITLE = "CreatedBook";

    private static final String UPDATED_BOOK_TITLE = "UpdatedBook";

    private static final Long BOOK_COUNT = 1L;

    @Autowired
    private BookService bookService;

    Genre genre;

    Author author;

    @BeforeEach
    void setUp() {
        genre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_TITLE);
        author = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
    }

    @Test
    void shouldReturnCorrectBookById() {

        var expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, genre, author);

        var actualBook = bookService.getById(EXISTING_BOOK_ID);

        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    void shouldCorrectCreateBook() {

        var createdBook = new Book(CREATED_BOOK_TITLE, genre, author);

        var createdBookId = bookService.create(createdBook);

        var expectedBook = new Book(createdBookId, CREATED_BOOK_TITLE, genre, author);

        var actualBook = bookService.getById(createdBookId);

        assertThat(bookService.getAll().size()).isEqualTo(2);
        assertThat(actualBook).isEqualTo(expectedBook);

    }

    @Test
    void shouldCorrectUpdateBookTitle() {

        var updatedBook = new Book(EXISTING_BOOK_ID, UPDATED_BOOK_TITLE, genre, author);

        bookService.update(updatedBook);

        var actualBook = bookService.getById(EXISTING_BOOK_ID);

        assertThat(actualBook).isEqualTo(updatedBook);

    }

    @Test
    void shouldCorrectDeleteBook() {

        var countBooks = bookService.getCount();

        var actualBook = bookService.getById(EXISTING_BOOK_ID);
        assertThat(actualBook).isNotNull();

        bookService.delete(EXISTING_BOOK_ID);

        assertThat(bookService.getCount()).isEqualTo(countBooks - 1);
        assertThat(bookService.getById(1)).isNull();

    }


    @Test
    void shouldReturnAllBooks() {

        var addedBook = new Book(CREATED_BOOK_TITLE, new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_TITLE), new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME));
        var bookId = bookService.create(addedBook);
        addedBook = bookService.getById(bookId);

        var expectedBook = new ArrayList<>();
        expectedBook.add(bookService.getById(1));
        expectedBook.add(addedBook);

        var actualBooks = bookService.getAll();

        assertThat(actualBooks)
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);

    }

    @Test
    void shouldReturnCorrectBookCount() {

        var countBook = bookService.getCount();

        assertThat(countBook).isEqualTo(BOOK_COUNT);

    }
}