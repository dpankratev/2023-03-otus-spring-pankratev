package ru.otus.spring.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.config.DaoConfig;
import ru.otus.spring.config.MessageConfig;
import ru.otus.spring.config.QuestionConfig;
import ru.otus.spring.service.MessageService;

import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Тест MessageServiceImplTest")
@MockBean(value = {DaoConfig.class, QuestionConfig.class})
@SpringBootTest
class MessageServiceImplTest {

    @Autowired
    private MessageService messageService;

    @MockBean
    private MessageConfig messageConfig;


    @DisplayName("возвращать корректное значение из appmessages по коду ask.name")
    @Test
    void getLocalizedMessageTest() {
        Mockito.when(messageConfig.getLocale()).thenReturn(Locale.forLanguageTag("en"));
        assertThat(messageService.getLocalizedMessage("ask.name"))
                .isEqualTo("Enter name");
    }

}