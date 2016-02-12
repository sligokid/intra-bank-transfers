package com.sligokid.origin.digital.bank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

public class AccountTest {

	@Test(expected = TransferException.class)
	public void acc_transfer_external_zero_funds_throws() {
		BigDecimal amount = new BigDecimal("50");
		Account payee = new Account();
		Account reciever = new Account();

		payee.performExternalTransfer(reciever, amount);

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

		assertEquals(account.getBalance(), BigDecimal.ZERO);
	}

	public void new_account_has_zero_transfers() {
		Account account = new Account();

		assertTrue(account.getTransactions().isEmpty());
	}

}
