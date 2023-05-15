package ru.otus.spring.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.MessageConfig;
import ru.otus.spring.service.MessageService;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageConfig messageConfig;

    private final MessageSource messageSource;

    @Override
    public String getLocalizedMessage(String code, String... args) {
        return messageSource.getMessage(code, args, messageConfig.getLocale());
    }

}
