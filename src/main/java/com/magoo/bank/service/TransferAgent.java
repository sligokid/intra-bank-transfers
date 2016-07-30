package com.magoo.bank.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.magoo.bank.TransferException;
import com.magoo.bank.TransferLimitException;
import com.magoo.bank.model.Account;
import com.magoo.bank.model.Customer;

public class TransferAgent {

	public void performInterTransfer(Customer from, Customer to, BigDecimal amount) {
		Account accountFrom = from.getAccounts().get(0);
		Account accountTo = to.getAccounts().get(0);

		if (accountFrom != null && accountTo != null) {
			accountFrom.performInternalTransfer(accountTo, amount);
		}
	}

	public void performIntraTransfer(Customer from, Customer to, BigDecimal amount) {
		Account accountFrom = from.getAccounts().get(0);
		Account accountTo = to.getAccounts().get(0);

		if (accountFrom != null && accountTo != null) {
			try {
				accountFrom.performExternalTransfer(accountTo, amount);
			} catch (TransferLimitException e) {
				breakDownTranfer(accountFrom, accountTo, amount);
			}
		}
	}

	private void breakDownTranfer(Account accountFrom, Account accountTo, BigDecimal amount) {
		BigDecimal limit = new BigDecimal("1000");

		List<BigDecimal> tranches = new ArrayList<BigDecimal>();
		boolean done = false;
		while (!done) {
			BigDecimal remainder = amount;
			amount = amount.subtract(limit);
			if (amount.compareTo(BigDecimal.ZERO) > 0) {
				tranches.add(limit);
			} else {
				tranches.add(remainder);
				done = true;
			}
		}

		System.out.println(tranches);

		for (BigDecimal value : tranches) {
			try {
				accountFrom.performExternalTransfer(accountTo, value);
			} catch (TransferLimitException e) {
				throw new TransferException("Transfer failed!");
			}
		}
	}

}
