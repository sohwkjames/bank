package com.example.bank.exceptions;

public class InvalidTransactionFormatException extends Exception {
    public InvalidTransactionFormatException() {
        super("Invalid transaction format");
    }

    public InvalidTransactionFormatException(String message) {
        super(message);
    }

}
