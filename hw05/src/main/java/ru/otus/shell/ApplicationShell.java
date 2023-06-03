package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.service.AuthorService;
import ru.otus.service.GenreService;
import ru.otus.service.impl.BookServiceImpl;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationShell {

    private final BookServiceImpl bookService;

    private final GenreService genreService;

    private final AuthorService authorService;

    @ShellMethod(value = "Create Book: book title, genre title, author title", key = {"c"})
    public long createBook(String title, String titleGenre, String titleAuthor) {
        return bookService.create(new Book(title, getGenre(titleGenre), getAuthor(titleAuthor)));
    }

    @ShellMethod(value = "Get Book by id", key = {"r", "get"})
    public Book getCount(long id) {
        return bookService.getById(id);
    }

    @ShellMethod(value = "Update Book: book id, book title, genre title, author title", key = {"u"})
    public void updateBook(String id, String title, String titleGenre, String titleAuthor) {
        var book = new Book(Long.parseLong(id), title, getGenre(titleGenre), getAuthor(titleAuthor));
        bookService.update(book);
    }

    @ShellMethod(value = "Delete Book by id", key = {"d"})
    public void deleteBook(long id) {
        bookService.delete(id);
    }

    @ShellMethod(value = "Get All Books", key = {"a"})
    public Object getAllBooks() {
        var allBooks = bookService.getAll();
        return allBooks.stream().map(Book::toString).collect(Collectors.joining(System.lineSeparator()));
    }

    private Author getAuthor(String titleAuthor) {
        Author author = authorService.getAll().stream()
                .filter(g -> g.getTitle().equalsIgnoreCase(titleAuthor))
                .findFirst()
                .orElse(null);

        if (author == null) {
            long authorId = authorService.create(new Author(titleAuthor));
            author = new Author(authorId, titleAuthor);
        }
        return author;
    }

    private Genre getGenre(String titleGenre) {
        Genre genre = genreService.getAll().stream()
                .filter(g -> g.getTitle().equalsIgnoreCase(titleGenre))
                .findFirst()
                .orElse(null);

        if (genre == null) {
            long genreId = genreService.create(new Genre(titleGenre));
            genre = new Genre(genreId, titleGenre);
        }
        return genre;
    }
}
