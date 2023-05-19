package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Person;
import ru.otus.spring.handlers.QuizResult;
import ru.otus.spring.service.QuestionFormatterService;
import ru.otus.spring.service.RunnerService;


@ShellComponent
@RequiredArgsConstructor
public class ApplicationShell {

    private final RunnerService runnerService;

    private final QuestionFormatterService formatterService;

    private Person person;

    private QuizResult quizResult;

    @ShellMethod(value = "Login", key = {"l", "login"})
    public void login(@ShellOption String userName) {
        this.person = new Person(userName);
    }

    @ShellMethod(value = "Start Quiz", key = {"s", "start"})
    public String start() {
        if (person == null) {
            return "Login before.";
        }
        quizResult = runnerService.startQuiz(person);
        return "Quiz completed. Press 'p' to print result.";
    }

    @ShellMethod(value = "Print last result", key = {"p", "print"})
    String printLastResult() {
        if (quizResult == null) {
            return "There is no completed quiz. Press 's' to start quiz.";
        }
        return formatterService.getTestResult(person, quizResult);
    }

}
