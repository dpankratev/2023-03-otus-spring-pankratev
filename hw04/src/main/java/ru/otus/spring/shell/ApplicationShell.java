package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
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

    private boolean isLogin;

    private boolean isQuizCompleted;

    @ShellMethod(value = "Login", key = {"l", "login"})
    public void login(@ShellOption String userName) {
        this.isLogin = true;
        this.person = new Person(userName);
    }

    @ShellMethod(value = "Start Quiz", key = {"s", "start"})
    @ShellMethodAvailability(value = "startAvailability")
    public String start() {
        quizResult = runnerService.startQuiz(person);
        this.isQuizCompleted = true;

        return "Quiz completed. Press 'p' to print result.";
    }

    @ShellMethod(value = "Print last result", key = {"p", "print"})
    @ShellMethodAvailability("printLastResultAvailability")
    public String printLastResult() {
        return formatterService.getTestResult(person, quizResult);
    }

    private Availability startAvailability() {
        return isLogin ? Availability.available() : Availability.unavailable("Login before.");
    }

    private Availability printLastResultAvailability() {
        return isQuizCompleted ? Availability.available() :
                Availability.unavailable("There is no completed quiz. Press 's' to start quiz.");
    }


}
