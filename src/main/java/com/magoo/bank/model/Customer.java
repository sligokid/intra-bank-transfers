package com.magoo.bank.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Customer {

	List<Account> accounts = new ArrayList<>();

	private String name;

	public Customer(String name) {
		this.accounts = new ArrayList<>();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public void createAccount(String name, BigDecimal balance) {
		Account account = new Account();
		account.setName(name);
		account.setBalance(balance);
		accounts.add(account);
	}

	@Override
	public String toString() {
		return "Customer [accounts=" + accounts + ", name=" + name + "]";
	}

}
