package com.magoo.bank.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.magoo.bank.TransferException;
import com.magoo.bank.TransferLimitException;

public class Account {

	private String name;

	private CurrencyCode currencyCode;

	// TODO Scaling and Rounding Mode HALF_EVEN
	private BigDecimal balance;

	private List<Transferable> transfers;

	public Account() {
		currencyCode = CurrencyCode.EUR;
		balance = BigDecimal.ZERO;
		transfers = new ArrayList<>();
	}

	public void performInternalTransfer(Account reciever, BigDecimal amount) {
		// TODO TX start
		checkBalance(amount);

		makePayment(reciever, amount, amount);

		Transferable transfer = new InterBankTransfer();
		recordTransaction(transfer, reciever, amount);
	}

	private void checkBalance(BigDecimal amount) {
		if (balance.compareTo(amount) < 0) {
			// ROLLBACK TX
			throw new TransferException("Insufficient Funds");
		}
	}

	private void makePayment(Account reciever, BigDecimal amount, BigDecimal total) {
		BigDecimal newBalance = balance.subtract(total);
		setBalance(newBalance);
		reciever.setBalance(reciever.getBalance().add(amount));
	}

	private void recordTransaction(Transferable transfer, Account reciever, BigDecimal amount) {
		transfer.setPayee(this);
		transfer.setReciever(reciever);
		transfer.setAmount(amount);
		transfers.add(transfer);
		reciever.addTransaction(transfer);
	}

	public void performExternalTransfer(Account reciever, BigDecimal amount) throws TransferLimitException {
		// TODO TX start
		Transferable transfer = new IntraBankTransfer();
		BigDecimal total = amount.add(transfer.getCommission());

		chekLimit(amount, transfer);

		checkBalance(total);

		makePayment(reciever, amount, total);

		recordTransaction(transfer, reciever, amount);
		// TOD TX end
	}

	private void chekLimit(BigDecimal amount, Transferable transfer) throws TransferLimitException {
		if (amount.compareTo(transfer.getLimit()) > 0) {
			// ROLLBACK TX
			throw new TransferLimitException("Transfer Limit excceded");
		}
	}

	public CurrencyCode getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(CurrencyCode currencyCode) {
		this.currencyCode = currencyCode;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public List<Transferable> getTransactions() {
		return transfers;
	}

	public void setTransactions(List<Transferable> transactions) {
		this.transfers = transactions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addTransaction(Transferable transaction) {
		transfers.add(transaction);
	}

	@Override
	public String toString() {
		return "Account [name=" + name + ", currencyCode=" + currencyCode + ", balance=" + balance + ", transfers="
				+ transfers + "]";
	}

}
