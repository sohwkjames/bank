package com.example.bank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.bank.exceptions.InvalidRateException;
import com.example.bank.exceptions.InvalidTransactionFormatException;
import com.example.bank.model.InterestRule;
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
				.setAmount(Double.parseDouble(parts[3]))
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

	public InterestRule getInterestRule(String interestRuleString) throws InvalidRateException {
		String[] parts = interestRuleString.split(" ");
		LocalDate date = getDateFromString(parts[0]);
		String ruleId = parts[1];
		Double rate =  Double.parseDouble(parts[2]);
		
		if (rate < 0 || rate >= 100) {
			throw new InvalidRateException();
		}
		
		InterestRule rule = new InterestRule(date, ruleId, rate);
		
		return rule;
	}

	public LocalDate getDateFromPrintStatementString(String printStatementString) {
		String[] parts = printStatementString.split(" ");
//		LocalDate date = getDateFromString(parts[1]);
		LocalDate date = LocalDate.parse(parts[1], DateTimeFormatter.ofPattern("yyyyMM"));
		
		return date;
		
	}

	public String getAccountIdFromPrintStatementString(String printStatementString) {
		String[] parts = printStatementString.split(" ");
		String accountId = parts[0];
		return accountId;
	}
	
	
}