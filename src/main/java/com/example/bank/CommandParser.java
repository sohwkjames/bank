package com.example.bank;

import com.example.bank.model.Transaction;

public class CommandParser {

	public CommandParser() {
		super();
	}
	
	public String getAccountId(String transactionString) throws Exception {
		// TODO Auto-generated method stub
		String[] parts = transactionString.split(" ");
		if (parts.length != 4) {
			throw new Exception("Invalid transaction detail format");
		}
		return parts[1];		
	}

	public Transaction getTransction(String transactionString) {
		// TODO Auto-generated method stub
		String[] parts = transactionString.split(" ");
		

		Transaction transaction = new Transaction.TransactionBuilder()
				.setAmount(parts);
		return null;
	}
	
	
}