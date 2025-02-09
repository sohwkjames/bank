package com.example.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

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
    	assertEquals(expected, actual);
    };
}
