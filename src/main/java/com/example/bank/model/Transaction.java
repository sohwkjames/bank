package com.example.bank.model;

import java.time.LocalDate;

public class Transaction {
	private String transactionId;
	private LocalDate date;
	private String type; // D, W, I
	private float amount;
	
	private Transaction(TransactionBuilder builder) {
		this.transactionId = builder.transactionId;
		this.date = builder.date;
		this.type = builder.type;
		this.amount = builder.amount;
	}
	
	public static class TransactionBuilder {
		private String transactionId;
		private LocalDate date;
		private String type; // D, W, I
		private float amount;
		
		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}
		
		public void setDate(LocalDate date) {
			this.date = date;
		}
		
		public void setType(String type) {
			this.type = type;
		}
		
		public void setAmount(float amount) {
			this.amount = amount;
		}	
		
		public Transaction build() {
			return new Transaction(this);
		}
	}
}
