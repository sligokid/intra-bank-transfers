package com.sligokid.origin.digital.bank;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	List<Account> accounts = new ArrayList<>();

	private String name;

	public Customer(String name) {
		this.accounts = new ArrayList<>();
		this.name = name;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
