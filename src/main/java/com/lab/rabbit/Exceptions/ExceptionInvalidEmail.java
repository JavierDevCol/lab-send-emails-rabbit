package com.lab.rabbit.Exceptions;

public class ExceptionInvalidEmail extends RuntimeException{

    public ExceptionInvalidEmail(String message) {
        super(message);
    }
}
