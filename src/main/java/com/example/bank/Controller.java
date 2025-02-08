package com.example.bank;

import java.util.Scanner;

import com.example.bank.model.Account;
import com.example.bank.model.Transaction;
import com.example.bank.service.BankService;

public class Controller {
	CommandParser commandParser;
	BankService bankService;
	
	public Controller(CommandParser commandParser, BankService bankService) {
		this.commandParser = commandParser;
		this.bankService = bankService;
	}

	public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
    		String welcomeMessage = "Welcome to AwesomeGIC Bank! What would you like to do?\n[T] Input transactions\n[I] Define interest rules\n[P] Print statement\n[Q] Quit ";
    		System.out.println(welcomeMessage);
    		
            String command = scanner.nextLine().trim().toLowerCase();
            
            if (command.equals("t")) {
            	System.out.println("Please enter transaction details in <Date> <Account> <Type> <Amount> format \n"
            			+ "(or enter blank to go back to main menu):\n");
            	
                String transactionString = scanner.nextLine().trim().toLowerCase();
                String accountId = commandParser.getAccountId(transactionString);
                Transaction transaction = commandParser.getTransction(transactionString);
                Account account = bankService.addTransactionToAccount(accountId, transaction);
                String commandParser.printTransactions(account.getTransactions());
            			
            } else if (command.equals("q")) {
            	System.out.println("Thank you for banking with AwesomeGIC Bank. \nHave a nice day!");
            }
            
            
        }
	}
}
