package com.example.bank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.bank.exceptions.InvalidTransactionFormatException;
import com.example.bank.model.Transaction;

public class CommandParser {

	public CommandParser() {
		super();
	}
	
	public String getAccountId(String transactionString) throws Exception {
		validateTransactionString(transactionString);
		String[] parts = transactionString.split(" ");

		return parts[1];		
	}

	public Transaction getTransction(String transactionString) throws Exception {
		validateTransactionString(transactionString);

		String[] parts = transactionString.split(" ");
		
		Transaction transaction = new Transaction.TransactionBuilder()
				.setDate(getDateFromString(parts[0]))
				.setType(parts[2])
				.setAmount(Float.parseFloat(parts[3]))
				.build();
		
		return transaction;
	}
	
	private void validateTransactionString(String s) throws InvalidTransactionFormatException {
		String[] parts = s.split(" ");
		if (parts.length != 4) {
			throw new InvalidTransactionFormatException();
		}
	}
	
	public LocalDate getDateFromString(String dateString) {
		return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
	
	
}