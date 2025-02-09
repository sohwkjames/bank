package com.example.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.example.bank.exceptions.InvalidRateException;
import com.example.bank.exceptions.InvalidTransactionFormatException;
import com.example.bank.model.Transaction;

public class CommandParserTest {
    @Test
    void testGetAccountId() throws Exception {
    	CommandParser parser = new CommandParser();
    	String incoming = "20230626 AC001 W 100.00";
    	String expected = "AC001";
    	
    	assertEquals(parser.getAccountId(incoming), expected);
    }
    
    @Test
    void testInvalidTransactionString() {
    	CommandParser parser = new CommandParser();
    	String incoming = "20230626 W 100.00";
        assertThrows(InvalidTransactionFormatException.class, () -> {
            parser.getAccountId(incoming);
        });
    }
    
    @Test
    void testGetTransaction() throws Exception {
    	CommandParser parser = new CommandParser();
    	String transactionString = "20230626 AC001 D 123.45";
    	Transaction actual = parser.getTransction(transactionString);
    	Transaction expected = new Transaction.TransactionBuilder()
    			.setAmount(123.45)
    			.setDate(LocalDate.of(2023, 06, 26))
    			.setType("D")
    			.build();
    	assertEquals(expected.getAmount(), actual.getAmount());
    	assertEquals(expected.getDate(), actual.getDate());
    	assertEquals(expected.getType(), actual.getType());
    	assertEquals(expected.getTransactionId(), actual.getTransactionId());
    };
    
    @Test
    void testGetDateFromString() {
    	CommandParser parser = new CommandParser();
    	String incoming = "20240102";
    	LocalDate expected = LocalDate.of(2024, 01, 02);
    	LocalDate actual = parser.getDateFromString(incoming);
    	assertEquals(expected, actual);
    }
    
    @Test
    void testGetInterestRateStringInvalidFormat() {
    	CommandParser parser = new CommandParser();
    	String incoming = "20230615 ABC 123 2.20";
    	assertThrows(InvalidTransactionFormatException.class, () -> {
            parser.getInterestRule(incoming);
        });
    }
    
    @Test
    void testGetInterestRateStringInvalidInterest() {
    	CommandParser parser = new CommandParser();
    	String incoming = "20230615 RULE03 100";
    	assertThrows(InvalidRateException.class, () -> {
            parser.getInterestRule(incoming);
        });
    }
}
