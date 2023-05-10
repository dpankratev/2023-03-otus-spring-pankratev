package ru.otus.spring.service;


import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    private final Scanner scanner;

    private final PrintStream outputStream;

    public IOServiceImpl() {
        this.scanner = new Scanner(System.in);
        this.outputStream = System.out;
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
