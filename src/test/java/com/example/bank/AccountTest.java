package com.example.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.bank.exceptions.InsufficientBalanceException;
import com.example.bank.model.Account;
import com.example.bank.model.Transaction;

public class AccountTest {
	private Account account;
	
	@BeforeEach
	void setUp() {
    	this.account = new Account("AC1");
	}
	
    @Test
    void testNewAccountShouldHaveNoTransactions() {
    	Account account = new Account("AC2");
    	assertEquals(account.getTransactions().size(), 0);
    	assertEquals(account.getBalance(), 0); 
    }	
    
    @Test
    void testTransactionsShouldComputeCorrectly() throws Exception {
    	Transaction t1 = new Transaction.TransactionBuilder()
    			.setAmount(100.20)
    			.setDate(LocalDate.of(2025, 1, 13))
    			.setType("D")
    			.build();
    	
    	Transaction t2 = new Transaction.TransactionBuilder()
    			.setAmount(99.1)
    			.setDate(LocalDate.of(2025, 1, 13))
    			.setType("D")
    			.build();
    	
    	Transaction t3 = new Transaction.TransactionBuilder()
    			.setAmount(99.1)
    			.setDate(LocalDate.of(2025, 1, 13))
    			.setType("D")
    			.build();
    	
    	account.addTransaction(t1);
    	account.addTransaction(t2);
    	
    	assertEquals(account.getBalance(), 199.30);
     
    }
    
    @Test
    void testRunningBalanceNotBelowZero() throws Exception {
    	Transaction t1 = new Transaction.TransactionBuilder()
    			.setAmount(100)
    			.setDate(LocalDate.of(2025, 1, 13))
    			.setType("D")
    			.build();
    	
    	Transaction t2 = new Transaction.TransactionBuilder()
    			.setAmount(99)
    			.setDate(LocalDate.of(2025, 1, 10))
    			.setType("W")
    			.build();
    	
    	account.addTransaction(t1);
        assertThrows(InsufficientBalanceException.class, () -> {
        	account.addTransaction(t2);
        });
    }
    
    @Test
    void testGenerateCreateStatement() throws Exception {
    	Transaction t1 = new Transaction.TransactionBuilder()
    			.setAmount(100)
    			.setDate(LocalDate.of(2024, 1, 13))
    			.setType("D")
    			.build();
    	
    	Transaction t2 = new Transaction.TransactionBuilder()
    			.setAmount(99)
    			.setDate(LocalDate.of(2024, 1, 13))
    			.setType("D")
    			.build();
    	
    	Transaction t3 = new Transaction.TransactionBuilder()
    			.setAmount(20)
    			.setDate(LocalDate.of(2024, 2, 1))
    			.setType("W")
    			.build();
    	
    	account.addTransaction(t1);
    	account.addTransaction(t2);
    	account.addTransaction(t3);
        String result = account.generateStatement();
        String expected = "Account: AC1\n| Date | Txn Id | Type | Amount |\n";
        expected += "| 20240113 | 20240113-01 | D | 100.00 |\n";
        expected += "| 20240113 | 20240113-02 | D | 99.00 |\n";
        expected += "| 20240201 | 20240201-01 | W | 20.00 |\n";
        
        assertEquals(expected, result);
    }
    
    @Test
    void testGenerateCreateFullStatement() throws Exception {
    	Transaction t1 = new Transaction.TransactionBuilder()
    			.setAmount(100)
    			.setDate(LocalDate.of(2024, 1, 13))
    			.setType("D")
    			.build();
    	
    	Transaction t2 = new Transaction.TransactionBuilder()
    			.setAmount(99)
    			.setDate(LocalDate.of(2024, 1, 13))
    			.setType("D")
    			.build();
    	
    	Transaction t3 = new Transaction.TransactionBuilder()
    			.setAmount(20)
    			.setDate(LocalDate.of(2024, 2, 1))
    			.setType("W")
    			.build();
    	
    	account.addTransaction(t1);
    	account.addTransaction(t2);
    	account.addTransaction(t3);
        String result = account.generateFullStatement();
        String expected = "Account: AC1\n| Date | Txn Id | Type | Amount |\n";
        expected += "20240113 | 20240113-01 | D | 100.00\n";
        expected += "20240113 | 20240113-02 | D | 99.00\n";
        expected += "20240201 | 20240201-01 | W | 20.00";
        
        assertEquals(expected, result);
    }
    
    
}
