package com.example.bank.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.bank.exceptions.InsufficientBalanceException;

public class Account {
	private String accountId;
	private List<Transaction> transactions;
	private double balance;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

	public Account(String accountId, List<Transaction> transactions, double balance) {
		super();
		this.accountId = accountId;
		this.transactions = transactions;
		this.balance = balance;
	}
	
	public Account(String accountId) {
		super();
		this.accountId = accountId;
		this.transactions = new ArrayList<>();
		this.balance = 0;
	}
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public Account addTransaction(Transaction transaction) throws Exception {
		String id = generateTransactionId(transaction.getDate());
		transaction.setTransactionId(id);
		
		if (transaction.getType().equals("D")) {
			this.balance += transaction.getAmount();
			transactions.add(transaction);
	        Collections.sort(transactions, Comparator.comparing(Transaction::getTransactionId));
		}
		
		if (transaction.getType().equals("W")) {
			transactions.add(transaction);
	        Collections.sort(transactions, Comparator.comparing(Transaction::getTransactionId));
	        double runningBalance = 0;
	        
	        // Checks that running balance is not negative at any point
	        for (Transaction txn : transactions) {
	        	if (txn.getType().equals("D")) {
	        		runningBalance += txn.getAmount();
	        	}
	        	
	        	if (txn.getType().equals("W")) {
	        		runningBalance -= txn.getAmount();
	        	}
	        	
	        	if (runningBalance < 0) {
	        		transactions.remove(transaction);
	        		throw new InsufficientBalanceException();
	        	}
	        }
	        
	        balance -= transaction.getAmount();
		}
		
		
		return this;

	}
	
    private String generateTransactionId(LocalDate date) {
        int n = getNextTransactionNumberForDate(date);
        return date.format(DATE_FORMAT) + "-" + String.format("%02d", n);
    }

    private int getNextTransactionNumberForDate(LocalDate date) {
        long count = transactions.stream()
                .filter(t -> t.getDate().equals(date))
                .count();
        return (int) count + 1;
    }

	public String generateStatement() {
		String result = "Account: " + this.getAccountId() +"\n";
		result += "| Date | Txn Id | Type | Amount |\n";
		for (Transaction t : transactions) {
			result += t.toString() + "\n";
		}
		
		return result;
	}

	public String generateFullStatement() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", transactions=" + transactions + ", balance=" + balance + "t]";
	}

	public void generateMonthlyStatement(List<InterestRule> yearMthRules) {
		// Get interest amount		
		// 
		
	}

	public void generateStatementForYearMonth(LocalDate date) {
		
	}	
	
}
