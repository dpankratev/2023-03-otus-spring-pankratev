package ru.otus.spring.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.service.IOService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест IOServiceImplTest")
class IOServiceImplTest {

    private static final String TEXT_TO_PRINT = "TEXT TO PRINT";

    private ByteArrayOutputStream bos;

    private IOService ioService;

    @BeforeEach
    void setUp() {
        bos = new ByteArrayOutputStream();
        ioService = new IOServiceImpl(System.in, new PrintStream(bos));
    }

    @DisplayName("должен печатать \"" + TEXT_TO_PRINT + "\"")
    @Test
    void shouldPrintText() {
        ioService.output(TEXT_TO_PRINT);
        assertThat(bos.toString()).isEqualTo(TEXT_TO_PRINT + System.lineSeparator());
    }

}