package com.example.bank;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.example.bank.model.Account;
import com.example.bank.model.InterestRule;
import com.example.bank.model.Transaction;
import com.example.bank.service.BankService;
import com.example.bank.service.InterestRuleService;

public class Controller {
	CommandParser commandParser;
	BankService bankService;
	InterestRuleService interestRuleService;

	public Controller(CommandParser commandParser, BankService bankService, InterestRuleService interestRuleService) {
		this.commandParser = commandParser;
		this.bankService = bankService;
		this.interestRuleService = interestRuleService;
	}

	public void start() {
		Scanner scanner = new Scanner(System.in);
		boolean isFirstMessage = true;
		String welcomeMessage;
		while (true) {
			if (isFirstMessage) {
				welcomeMessage = "Welcome to AwesomeGIC Bank! What would you like to do?\n[T] Input transactions\n[I] Define interest rules\n[P] Print statement\n[Q] Quit ";
			} else {
				welcomeMessage = "Is there anything else you'd like to do?\\n[T] Input transactions\\n[I] Define interest rules\\n[P] Print statement\\n[Q] Quit ";
			}
			System.out.println(welcomeMessage);

			String command = scanner.nextLine().trim().toLowerCase();

			if (command.equals("t")) {
				System.out.println("Please enter transaction details in <Date> <Account> <Type> <Amount> format \n"
						+ "(or enter blank to go back to main menu):\n");
				try {
					String transactionString = scanner.nextLine().trim();

					String accountId = commandParser.getAccountId(transactionString);
					Transaction transaction = commandParser.getTransction(transactionString);
					Account account = bankService.addTransactionToAccount(accountId, transaction);

					System.out.println(account.generateStatement());
					isFirstMessage = false;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			} else if (command.equals("i")) {
				System.out.println("Please enter interest rules details in <Date> <RuleId> <Rate in %> format \n"
						+ "(or enter blank to go back to main menu):\n");

				try {
					String interestRuleString = scanner.nextLine().trim();
					InterestRule interestRule = commandParser.getInterestRule(interestRuleString);
					interestRuleService.addInterestRule(interestRule);
					System.out.println(interestRuleService.generateInterestRulesStatement());
					isFirstMessage = false;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else if (command.equals("p")) {
				System.out.println("Please enter account and month to generate the statement <Account> <Year> <Month>\n"
						+ "(or enter blank to go back to main menu):\n");
				String printStatementString = scanner.nextLine().trim();
				
				LocalDate date = commandParser.getDateFromPrintStatementString(printStatementString);
				String accountId = commandParser.getAccountIdFromPrintStatementString(printStatementString);
				Account acc = bankService.getAccountById(accountId);
				List<InterestRule> yearMthRules = interestRuleService.getRulesFromYearMonth(date);
				
				acc.generateStatementForYearMonth(date);
//				Work in progress
//				acc.generateMonthlyStatement(yearMthRules);
			}

			else if (command.equals("q")) {
				System.out.println("Thank you for banking with AwesomeGIC Bank. \nHave a nice day!");
				return;
			}
		}
	}

}
