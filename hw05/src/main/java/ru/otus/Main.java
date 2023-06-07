package ru.otus;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Genre;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class);
        GenreDao dao = context.getBean(GenreDao.class);

        var book = context.getBean(BookDao.class);

        System.out.println("Count: " + dao.count());
        dao.insert(new Genre(2, "title1"));
        System.out.println("Count: " + dao.count());

        System.out.println("@@@: " + book.getById(1));


    }
}
