package com.example.turboaz.exceptions;

public class UsernameIsAlreadyTakenException extends Exception{
    public UsernameIsAlreadyTakenException(String message) {
        super(message);
    }
}
