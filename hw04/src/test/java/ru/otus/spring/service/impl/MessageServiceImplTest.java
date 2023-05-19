package ru.otus.spring.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.config.MessageConfig;
import ru.otus.spring.service.MessageService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Тест MessageServiceImplTest")
@SpringBootTest
class MessageServiceImplTest {

    @Autowired
    MessageService messageService;

    @Autowired
    MessageConfig messageConfig;


    @DisplayName("возвращать корректное значение из appmessages по коду ask.name")
    @Test
    void getLocalizedMessageTest() {
        assertThat(messageService.getLocalizedMessage("ask.name"))
                .isEqualTo("Enter name");
    }

}