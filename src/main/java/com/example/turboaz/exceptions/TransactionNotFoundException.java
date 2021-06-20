package com.example.turboaz.exceptions;

public class TransactionNotFoundException extends Exception{
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
