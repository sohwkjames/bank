package com.example.bank.model;

import java.util.List;

public class Account {
	private String accountId;
	private List<Transaction> transactions;
	private float balance;
	public Account(String accountId, List<Transaction> transactions, float balance) {
		super();
		this.accountId = accountId;
		this.transactions = transactions;
		this.balance = balance;
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
}
