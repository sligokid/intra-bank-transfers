package com.magoo.bank;

import java.math.BigDecimal;
import java.util.List;

import com.magoo.bank.model.Bank;
import com.magoo.bank.model.Customer;
import com.magoo.bank.service.TransferAgent;

public class BankSystem {

	private Bank bankA;

	private Bank bankB;

	private TransferAgent transferAgent;

	public BankSystem() {
		bankA = new Bank();
		bankB = new Bank();
		transferAgent = new TransferAgent();
	}

	public TransferAgent getTransferAgent() {
		return transferAgent;
	}

	public Bank getBankA() {
		return bankA;
	}

	public void setBankA(Bank bankA) {
		this.bankA = bankA;
	}

	public Bank getBankB() {
		return bankB;
	}

	public void setBankB(Bank bankB) {
		this.bankB = bankB;
	}

	private void makeTransfer(Customer from, Customer to, BigDecimal amount) {
		List<Customer> aList = bankA.getCustomers();
		List<Customer> bList = bankB.getCustomers();

		// same bank
		if ((aList.indexOf(from) > -1 && aList.indexOf(to) > -1)
				|| (bList.indexOf(from) > -1 && bList.indexOf(to) > -1)) {
			transferAgent.performInterTransfer(from, to, amount);
		} else {
			transferAgent.performIntraTransfer(from, to, amount);
		}

	}

	public static void main(String[] args) {
		BankSystem bankSystem = new BankSystem();

		Bank bankA = bankSystem.getBankA();
		Customer jose = new Customer("Jose");
		jose.createAccount("Current Account", new BigDecimal("40000"));
		bankA.addCustomer(jose);

		Bank bankB = bankSystem.getBankB();
		Customer antonio = new Customer("Antonio");
		antonio.createAccount("Current Account", new BigDecimal("30000"));
		Customer maria = new Customer("Maria");
		maria.createAccount("Current Account", BigDecimal.ZERO);

		bankB.addCustomer(antonio);
		bankB.addCustomer(maria);

		bankSystem.setBankA(bankA);
		bankSystem.setBankB(bankB);

		// Antonio pays Maria within the same bank.
		bankSystem.makeTransfer(antonio, maria, new BigDecimal("20000"));
		bankSystem.makeTransfer(jose, maria, new BigDecimal("20000"));

		System.out.println(antonio);
		System.out.println(jose);
		System.out.println(maria);
	}

}
