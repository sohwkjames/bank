package com.example.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.bank.model.Account;
import com.example.bank.service.BankService;

public class BankServiceTest {
	private BankService bankService;
	private Account account1;
	private Account account2;
	
	@BeforeEach
	void setUp() {
    	List<Account> accounts = new ArrayList<>();
    	account1 = new Account("AC1", new ArrayList<>(), 0);
    	account2 = new Account("AC2", new ArrayList<>(), 0);
    	accounts.add(account1);
    	accounts.add(account2);
    	
    	this.bankService = new BankService(accounts);
	}
	
    @Test
    void testGetAccountValidId() {
    	Account acc = bankService.getAccountById("AC1");
    	assertEquals(acc, account1);
    }
    
    @Test
    void testGetAccountInvalidId() throws Exception {
    	Account account = bankService.getAccountById("ABC");
    	assertEquals(account, null);
    }
}
