package ru.otus.spring.service.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.IOService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    private final Scanner scanner;

    private final PrintStream outputStream;

    public IOServiceImpl(@Value("#{T(java.lang.System).in}") InputStream in,
                         @Value("#{T(java.lang.System).out}") PrintStream out) {
        this.scanner = new Scanner(in);
        this.outputStream = out;
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
