package com.example.bank.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.bank.model.InterestRule;

public class InterestRuleService {
	private List<InterestRule> interestRules;
	
	public InterestRuleService() {
		super();
		this.interestRules = new ArrayList<>();
	}

	public List<InterestRule> getInterestRules() {
		return interestRules;
	}

	public void setInterestRules(List<InterestRule> interestRules) {
		this.interestRules = interestRules;
	}

	public InterestRule addInterestRule(InterestRule rule) {
		// Checks if there is rule with existing same date
		boolean ruleReplaced = false;
		for (InterestRule existingRule : interestRules) {
			if (existingRule.getDate().equals(rule.getDate())) {
				existingRule.setRate(rule.getRate());
				existingRule.setRuleId(rule.getRuleId());
				ruleReplaced = true;
			}
		}
		
		if (!ruleReplaced) {
			interestRules.add(rule);
		}
		
        Collections.sort(interestRules, Comparator.comparing(InterestRule::getDate));
		
		return rule;
	}
	
	public String generateInterestRulesStatement() {
		String result = "Interest rules:\n| Date | RuleId | Rate (%) |\n";
		for (InterestRule rule : interestRules) {
			result += rule.toString() + "\n";
		}
		
		return result;
	}

	public List<InterestRule> getRulesFromYearMonth(LocalDate date) {
        YearMonth targetYearMonth = YearMonth.from(date);
        return interestRules.stream()
                .filter(rule -> YearMonth.from(rule.getDate()).equals(targetYearMonth))
                .collect(Collectors.toList());		
	}
	
}
