package com.example.bank.exceptions;

public class InvalidRateException extends Exception {

	public InvalidRateException() {
		super("Interest Rate must be > 0 and < 100");
	}

}
