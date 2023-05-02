package ru.otus.spring.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    private final Scanner scanner;

    private final PrintStream outputStream;

    public IOServiceImpl(@Value("${ioservice.input}") InputStream inputStream,
                         @Value("${ioservice.output}") PrintStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.outputStream = outputStream;

    }

    @Override
    public String input() {
        return scanner.next();
    }

    @Override
    public void output(String message) {

        outputStream.println(message);

    }
}
