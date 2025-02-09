package com.example.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.example.bank.model.InterestRule;
import com.example.bank.service.InterestRuleService;

public class InterestRuleServiceTest {
//	@BeforeEach
//	void setUp() {
//    	List<InterestRule> rules = new ArrayList<>();
//    	rule1 = new Account("AC1", new ArrayList<>(), 0);
//    	account2 = new Account("AC2", new ArrayList<>(), 0);
//    	accounts.add(account1);
//    	accounts.add(account2);
//    	
//    	this.bankService = new BankService(accounts);
//	}
	
    @Test
    void testKeepOnlyLatestRuleIfSameDate() {
    	InterestRuleService irs = new InterestRuleService();
    	InterestRule rule1 = new InterestRule(LocalDate.of(2025, 1, 1), "R1", 5.1);
    	InterestRule rule2 = new InterestRule(LocalDate.of(2025, 1, 2), "R2", 1.2);
    	InterestRule rule3 = new InterestRule(LocalDate.of(2025, 1, 1), "R3", 3.3);
    	
    	irs.addInterestRule(rule1);
    	irs.addInterestRule(rule2);
    	irs.addInterestRule(rule3);
    	
    	assertEquals(irs.getInterestRules().size(), 2);
    	
    	assertEquals(irs.getInterestRules().get(0).getRate(), rule3.getRate());
    	assertEquals(irs.getInterestRules().get(0).getRuleId(), rule3.getRuleId());
    }
    
    @Test
    void testGenerateStatement() {
    	InterestRuleService irs = new InterestRuleService();
    	InterestRule rule1 = new InterestRule(LocalDate.of(2025, 1, 1), "R1", 5.1);
    	InterestRule rule2 = new InterestRule(LocalDate.of(2025, 1, 2), "R2", 1.2);
    	InterestRule rule3 = new InterestRule(LocalDate.of(2025, 1, 1), "R3", 3.3);
    	
    	irs.addInterestRule(rule1);
    	irs.addInterestRule(rule2);
    	irs.addInterestRule(rule3);
    	
    	String actual = irs.generateInterestRulesStatement();
    	String expected = "Interest rules:\n| Date | RuleId | Rate (%) |\n";
    	expected += "| 20250101 | R3 | 3.30 |\n";
    	expected += "| 20250102 | R2 | 1.20 |\n";
    	
    	assertEquals(expected, actual);
    }
}
