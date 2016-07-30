package com.magoo.bank.model;

import java.math.BigDecimal;

public abstract class AbstractTransfer {

	private Account payee;

	private Account reciever;

	private BigDecimal amount;

	public AbstractTransfer() {
		super();
	}

	public Account getPayee() {
		return payee;
	}

	public Account getReciever() {
		return reciever;
	}

	public void setPayee(Account payee) {
		this.payee = payee;
	}

	public void setReciever(Account reciever) {
		this.reciever = reciever;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "AbstractTransfer [payee=" + payee.getName() + ", reciever=" + reciever.getName() + ", amount=" + amount
				+ "]";
	}

}