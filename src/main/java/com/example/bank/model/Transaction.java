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
		
		public TransactionBuilder setTransactionId(String transactionId) {
			this.transactionId = transactionId;
			return this;
		}
		
		public TransactionBuilder setDate(LocalDate date) {
			this.date = date;
			return this;
		}
		
		public TransactionBuilder setType(String type) {
			this.type = type;
			return this;
		}
		
		public TransactionBuilder setAmount(float amount) {
			this.amount = amount;
			return this;
		}	
		
		public Transaction build() {
			return new Transaction(this);
		}
	}
}
