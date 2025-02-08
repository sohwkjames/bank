package com.example.bank.service;
import java.util.ArrayList;
import java.util.List;

import com.example.bank.model.Account;
import com.example.bank.model.Transaction;

public class BankService {
	private List<Account> accounts = new ArrayList<>();
//	private InterestRuleService interestService;
	
	public BankService(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public BankService() {	
	}
	
	public Account getAccountById(String accountId) {
		for (Account account : accounts) {
			if (account.getAccountId().equals(accountId)) {
				return account;
			}
		}
		
		return null;
	}

	public Account addTransactionToAccount(String accountId, Transaction transaction) throws Exception {
		// Fetch the account
		Account account;
		account = getAccountById(accountId);
		if (account == null) {
			account = new Account(accountId);
			accounts.add(account);
		}

		account.addTransaction(transaction);
		
		return account;
	}
}
