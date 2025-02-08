package com.example.bank;

import com.example.bank.service.BankService;

public class Main {

	public static void main(String[] args) {
		CommandParser parser = new CommandParser();
		BankService bankService = new BankService();
		Controller controller = new Controller(parser, bankService);		
		controller.start();
	}

}
