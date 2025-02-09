package com.example.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.bank.exceptions.InvalidTransactionFormatException;

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
        
//        assertEquals(exception.getMessage(), "Invalid transaction detail format");    	
    }
}
