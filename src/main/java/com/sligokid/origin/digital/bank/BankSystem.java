package com.sligokid.origin.digital.bank;

public class BankSystem {

	private Bank bankA;

	private Bank bankB;

	public static void main(String[] args) {
		BankSystem bankSystem = new BankSystem();

		Bank bankA = bankSystem.getBankA();
		Customer jose = new Customer("Jose");
		bankA.addCustomer(jose);

		Bank bankB = bankSystem.getBankB();
		Customer antonio = new Customer("Antonio");
		Customer maria = new Customer("Maria");
		bankB.addCustomer(antonio);
		bankB.addCustomer(maria);

		// Time has run out here... create the TA and manage the brokerage /
		// transfer failures
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

}
