package com.example.turboaz.exceptions;

public class AmountGreaterThanZeroException extends Exception{
    public AmountGreaterThanZeroException(String message) {
        super(message);
    }
}
