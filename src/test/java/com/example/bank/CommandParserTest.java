package com.example.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandParserTest {
    @Test
    void testGetAccountId() {
    	CommandParser parser = new CommandParser();
    	String incoming = "20230626 AC001 W 100.00";
    	String expected = "AC001";
    	
    	assertEquals(parser.getAccountId(incoming), expected);
    }
}
