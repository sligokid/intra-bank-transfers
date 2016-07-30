package com.magoo.bank.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.magoo.bank.model.Bank;
import com.magoo.bank.model.Customer;

public class BankTest {

	@Test
	public void bank_name_is_myBank() {
		Bank bank = new Bank();
		bank.setName("myBank");

		assertEquals("myBank", bank.getName());
	}

	@Test
	public void bank_customer_is_added() {
		Bank bank = new Bank();
		Customer customer1 = new Customer("Amy");

		bank.addCustomer(customer1);

		assertEquals(1, bank.getCustomers().size());
	}

	@Test
	public void bank_customer_is_removed() {
		Bank bank = new Bank();
		Customer customer1 = new Customer("Tom");
		bank.addCustomer(customer1);

		bank.removeCustomer(customer1);
		assertTrue(bank.getCustomers().isEmpty());
	}
}
