package ru.otus.spring.service;

import java.io.IOException;
import java.io.OutputStream;


public class IOServiceImpl implements IOService {

    private final OutputStream outputStream;

    public IOServiceImpl(OutputStream outputStream) {
        this.outputStream = outputStream;

    }

    @Override
    public byte[] input() {
        return new byte[0];
    }

    @Override
    public void output(byte[] bytes) {
        try {
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
