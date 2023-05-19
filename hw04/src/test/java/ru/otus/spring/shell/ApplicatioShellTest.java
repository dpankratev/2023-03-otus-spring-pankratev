package ru.otus.spring.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.test.ShellAssertions;
import org.springframework.shell.test.ShellTestClient;
import org.springframework.shell.test.autoconfigure.ShellTest;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.QuestionFormatterService;
import ru.otus.spring.service.RunnerService;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@DisplayName("Тест ApplicatioShellTest")
@ShellTest
public class ApplicatioShellTest {

    @Autowired
    ShellTestClient client;

    @MockBean
    QuestionFormatterService questionFormatterService;

    @MockBean
    IOService ioService;

    @MockBean
    RunnerService runnerService;

    @DisplayName("должен содержать Start Quiz в команде help")
    @Test
    void shouldContainsStartQuizInHelpCommand() {

        ShellTestClient.InteractiveShellSession session = client
                .interactive()
                .run();

        session.write(session.writeSequence().text("help").carriageReturn().build());

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText("Start Quiz");
        });

    }

    @DisplayName("должен содержать корректный ответ в команде print, без прохождения quiz")
    @Test
    void shouldPrintMessageWhenNoQuizPassed() {
        ShellTestClient.InteractiveShellSession session = client
                .interactive()
                .run();

        session.write(session.writeSequence().text("p").carriageReturn().build());
        await().atMost(1, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText("There is no completed quiz. Press 's' to start quiz.");
        });

    }
}
