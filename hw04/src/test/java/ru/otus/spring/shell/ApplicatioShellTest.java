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
import static org.springframework.shell.test.ShellAssertions.assertThat;

@DisplayName("Тест ApplicatioShellTest")
@MockBean(value = {QuestionFormatterService.class, IOService.class, RunnerService.class})
@ShellTest
public class ApplicatioShellTest {

    @Autowired
    private ShellTestClient client;


    @DisplayName("должен содержать Start Quiz в команде help")
    @Test
    public void shouldContainsStartQuizInHelpCommand() {

        ShellTestClient.InteractiveShellSession session = client
                .interactive()
                .run();

        session.write(session.writeSequence().text("help").carriageReturn().build());

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            assertThat(session.screen())
                    .containsText("Start Quiz");
        });

    }

    @DisplayName("Не должен принимать команду print, без прохождения quiz")
    @Test
    public void shouldPrintMessageWhenNoQuizPassed() {
        ShellTestClient.InteractiveShellSession session = client
                .interactive()
                .run();

        session.write(session.writeSequence().text("help").carriageReturn().build());
        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen()).containsText("* p");
        });
    }
}
