package com.example.bank.exceptions;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException() {
        super("Insufficient balance for the transaction.");
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
