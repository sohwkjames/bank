package com.example.bank.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account {
	private String accountId;
	private List<Transaction> transactions;
	private float balance;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

	public Account(String accountId, List<Transaction> transactions, float balance) {
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
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	public Account addTransaction(Transaction transaction) throws Exception {
		String id = generateTransactionId(transaction.getDate());
		transaction.setTransactionId(id);
		
		if (transaction.getType().equals("D")) {
			this.balance += transaction.getAmount();
		}
		
		if (transaction.getType().equals("W")) {
			if (transactions.size() == 0) {
				throw new Exception("First transaction should not be a withdrawal");
			}
			
			if (this.balance - transaction.getAmount() < 0) {
				throw new Exception("Cannot withdraw more than available balance");
			}
			
			this.balance -= transaction.getAmount();
		}
		
		transactions.add(transaction);
		
		return this;

	}
	
//	public List<Transaction> getTransactionsByDate(LocalDate date) {
//        List<Transaction> result = new ArrayList<>();
//        for (Transaction transaction : transactions) {
//            if (transaction.getDate().equals(date)) {
//                result.add(transaction);
//            }
//        }
//        return result;
//	}
//	

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
		String result = "Account: " + this.getAccountId();
		for (Transaction t : transactions) {
			result += t.toString() + "\n";
		}
		
		return result;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", transactions=" + transactions + ", balance=" + balance + "]";
	}

	
	
//	@Override
//	public String toString() {
//		String result = "";
//		result += "Account: " + accountId + "\n";
//		result += "| Date | TxnId | Type | Amount |" + "\n";
//		for (Transaction t : transactions) {
//			result += t.toString() + "\n";
//		}
//		
//		return result;
//	}
	
	
}
