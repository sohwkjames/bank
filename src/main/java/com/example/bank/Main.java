package com.example.bank;

import com.example.bank.service.BankService;
import com.example.bank.service.InterestRuleService;

public class Main {

	public static void main(String[] args) {
		CommandParser parser = new CommandParser();
		BankService bankService = new BankService();
		InterestRuleService interestRuleService = new InterestRuleService();
		Controller controller = new Controller(parser, bankService, interestRuleService);		
		controller.start();
	}

}
