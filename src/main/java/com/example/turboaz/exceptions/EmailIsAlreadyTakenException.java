package com.example.turboaz.exceptions;

public class EmailIsAlreadyTakenException extends Exception{
    public EmailIsAlreadyTakenException(String message) {
        super(message);
    }
}
