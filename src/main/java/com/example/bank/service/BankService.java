package com.example.bank.service;
import java.util.ArrayList;
import java.util.List;

import com.example.bank.model.Account;

public class BankService {
	private List<Account> accounts = new ArrayList<>();
//	private InterestRuleService interestService;
	
	public BankService(List<Account> accounts) {
		super();
		this.accounts = accounts;
	}
	
	public BankService() {	
	}

	boolean addAccount(Account acc) {
		return true;
	}
	
	Account getAccountById(String accountId) {
		return null;
	}
}
