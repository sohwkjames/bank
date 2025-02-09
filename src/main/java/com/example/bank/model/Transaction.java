package com.example.bank.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
	private String transactionId;
	private LocalDate date;
	private String type; // D, W, I
	private double amount;

	private Transaction(TransactionBuilder builder) {
		this.transactionId = builder.transactionId;
		this.date = builder.date;
		this.type = builder.type;
		this.amount = builder.amount;
	}
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedAmount = String.format("%.2f", amount);
		return "| " + date.format(formatter) + " | " + transactionId + " | " + type + " | "
				+ formattedAmount + " |";
	}
	
	public static class TransactionBuilder {
		private String transactionId;
		private LocalDate date;
		private String type; // D, W, I
		private double amount;
		
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
		
		public TransactionBuilder setAmount(double amount) {
			this.amount = amount;
			return this;
		}	
		
		public Transaction build() {
			return new Transaction(this);
		}
	}
}
