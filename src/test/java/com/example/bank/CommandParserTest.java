package com.example.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CommandParserTest {
    @Test
    void testGetAccountId() throws Exception {
    	CommandParser parser = new CommandParser();
    	String incoming = "20230626 AC001 W 100.00";
    	String expected = "AC001";
    	
    	assertEquals(parser.getAccountId(incoming), expected);
    }
    
    @Test
    void testInvalidTransactionString() throws Exception {
    	CommandParser parser = new CommandParser();
    	String incoming = "20230626 W 100.00";
        Exception exception = assertThrows(Exception.class, () -> {
            parser.getAccountId(incoming);
        });
        
        assertEquals(exception.getMessage(), "Invalid transaction detail format");    	
    }
}
