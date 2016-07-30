package com.magoo.bank.model;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.magoo.bank.model.IntraBankTransfer;
import com.magoo.bank.model.Transferable;

public class IntraBankTransferTest {

	@Test
	public void intra_bank_transfer_has_five_euro_comm() {
		Transferable transfer = new IntraBankTransfer();
		BigDecimal expected = new BigDecimal("5");

		assertTrue(transfer.getCommission().compareTo(expected) == 0);
	}

	@Test
	public void intra_bank_transfer_has_limit_of_1000() {
		Transferable transfer = new IntraBankTransfer();
		BigDecimal expected = new BigDecimal("1000");

		assertTrue(transfer.getLimit().compareTo(expected) == 0);
	}

}
