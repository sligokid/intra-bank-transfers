package com.magoo.bank.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.magoo.bank.TransferException;
import com.magoo.bank.TransferLimitException;
import com.magoo.bank.model.Account;
import com.magoo.bank.model.CurrencyCode;
import com.magoo.bank.model.Transferable;

public class AccountTest {

	@Test
	public void acc_transfer_external_complete_successfully() throws TransferLimitException {
		BigDecimal amount = new BigDecimal("50");
		Account payee = new Account();
		payee.setBalance(new BigDecimal("1000"));
		Account reciever = new Account();
		BigDecimal payeeExpected = new BigDecimal("945");
		BigDecimal recExpected = new BigDecimal("50");

		payee.performExternalTransfer(reciever, amount);

		assertTrue(payee.getBalance().compareTo(payeeExpected) == 0);
		assertTrue(reciever.getBalance().compareTo(recExpected) == 0);
		assertEquals(1, payee.getTransactions().size());
		assertEquals(1, reciever.getTransactions().size());
	}

	@Test(expected = TransferLimitException.class)
	public void acc_transfer_external_over_limit_funds_throws() throws TransferLimitException {
		BigDecimal amount = new BigDecimal("50000");
		Account payee = new Account();
		Account reciever = new Account();

		payee.performExternalTransfer(reciever, amount);
	}

	@Test(expected = TransferException.class)
	public void acc_transfer_external_zero_funds_throws() throws TransferLimitException {
		BigDecimal amount = new BigDecimal("50");
		Account payee = new Account();
		Account reciever = new Account();

		payee.performExternalTransfer(reciever, amount);
	}

	@Test
	public void acc_transfer_log_contains_transfer_details() {
		BigDecimal amount = new BigDecimal("50");
		Account payee = new Account();
		payee.setBalance(new BigDecimal("1000"));
		Account reciever = new Account();
		payee.performInternalTransfer(reciever, amount);

		Transferable transfer = payee.getTransactions().get(0);

		assertEquals(reciever, transfer.getReciever());
		assertEquals(amount, transfer.getAmount());
	}

	@Test
	public void acc_transfer_in_house_complete_successfully() {
		BigDecimal amount = new BigDecimal("50");
		Account payee = new Account();
		payee.setBalance(new BigDecimal("1000"));
		Account reciever = new Account();
		BigDecimal payeeExpected = new BigDecimal("950");
		BigDecimal recExpected = new BigDecimal("50");

		payee.performInternalTransfer(reciever, amount);

		assertTrue(payee.getBalance().compareTo(payeeExpected) == 0);
		assertTrue(reciever.getBalance().compareTo(recExpected) == 0);
		assertEquals(1, payee.getTransactions().size());
		assertEquals(1, reciever.getTransactions().size());
	}

	@Test(expected = TransferException.class)
	public void acc_transfer_in_house_ins_funds_throws() {
		BigDecimal amount = new BigDecimal("50");
		Account payee = new Account();
		payee.setBalance(new BigDecimal("49"));
		Account reciever = new Account();

		payee.performInternalTransfer(reciever, amount);
	}

	@Test(expected = TransferException.class)
	public void acc_transfer_in_house_zero_funds_throws() {
		BigDecimal amount = new BigDecimal("50");
		Account payee = new Account();
		Account reciever = new Account();

		payee.performInternalTransfer(reciever, amount);
	}

	@Test
	public void acc_name_is_myAcc() {
		Account account = new Account();
		account.setName("myAcc");

		assertEquals("myAcc", account.getName());
	}

	@Test
	public void default_currency_is_eur() {
		Account account = new Account();
		account.setCurrencyCode(CurrencyCode.EUR);

		assertEquals(CurrencyCode.EUR, account.getCurrencyCode());
	}

	@Test
	public void new_account_has_zero_balance() {
		Account account = new Account();

		assertEquals(BigDecimal.ZERO, account.getBalance());
	}

	@Test
	public void new_account_has_zero_transfers() {
		Account account = new Account();

		assertTrue(account.getTransactions().isEmpty());
	}

}
