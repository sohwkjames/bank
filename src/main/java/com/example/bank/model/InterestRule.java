package com.example.bank.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InterestRule {
	private LocalDate date;
	private String ruleId;
	private double rate;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
	
		public InterestRule(LocalDate date, String ruleId, double rate) {
			super();
			this.date = date;
			this.ruleId = ruleId;
			this.rate = rate;
		}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedRate = String.format("%.2f", rate);

		return "| " + date.format(formatter) + " | " + ruleId + " | " + formattedRate + " |";
	}
	
	
}
