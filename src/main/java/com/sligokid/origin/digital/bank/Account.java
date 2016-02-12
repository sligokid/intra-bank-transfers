package com.sligokid.origin.digital.bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
		if (balance.compareTo(amount) < 0) {
			// ROLLBACK TX
			throw new TransferException("Insufficient Funds");
		}
		setBalance(balance.subtract(amount));
		reciever.setBalance(reciever.getBalance().add(amount));
		// TOD TX end
	}

	public void performExternalTransfer(Account reciever, BigDecimal amount) {
		// TODO TX start
		Transferable transfer = new IntraBankTransfer();
		BigDecimal total = amount.add(transfer.getCommission());

		if (balance.compareTo(total) < 0) {
			// ROLLBACK TX
			throw new TransferException("Insufficient Funds");
		}

		if (amount.compareTo(transfer.getLimit()) >= 0) {
			// ROLLBACK TX
			throw new TransferException("Transfer Limit excceded");
		}
		setBalance(balance.subtract(total));
		reciever.setBalance(reciever.getBalance().add(amount));
		// TOD TX end
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

}
