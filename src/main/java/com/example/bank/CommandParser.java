package com.example.bank;

import java.util.Scanner;

public class CommandParser {
	
	
	
	public CommandParser() {
	
	}

	public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
    		String welcomeMessage = "Welcome to AwesomeGIC Bank! What would you like to do?\n[T] Input transactions\n[I] Define interest rules\n[P] Print statement\n[Q] Quit ";
    		System.out.println(welcomeMessage);
    		
            String command = scanner.nextLine().trim().toLowerCase();
            
            if (command.equals("T")) {
            	System.out.println("Please enter transaction details in <Date> <Account> <Type> <Amount> format \n"
            			+ "(or enter blank to go back to main menu):\n");
            			
            }
        }
        
	}
}
