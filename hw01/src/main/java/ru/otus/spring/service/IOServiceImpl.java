package ru.otus.spring.service;


import java.io.PrintStream;


public class IOServiceImpl implements IOService {

    private final PrintStream outputStream;

    public IOServiceImpl(PrintStream outputStream) {
        this.outputStream = outputStream;

    }

    @Override
    public String input() {
        return null;
    }

    @Override
    public void output(String message) {

        outputStream.println(message);

    }
}
