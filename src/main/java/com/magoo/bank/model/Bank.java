package com.magoo.bank.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bank {

	private String name;

	private List<Customer> customers;

	public Bank() {
		customers = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addCustomer(Customer customer1) {
		customers.add(customer1);
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void removeCustomer(Customer customer1) {
		for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext();) {
			Customer customer = iterator.next();
			if (customer.equals(customer1)) {
				iterator.remove();
			}
		}
	}
}
