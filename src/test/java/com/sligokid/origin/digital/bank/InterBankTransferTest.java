package com.sligokid.origin.digital.bank;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

public class InterBankTransferTest {

	@Test
	public void inter_bank_transfer_has_zero_comm() {
		Transferable transfer = new InterBankTransfer();

		assertTrue(transfer.getCommission().compareTo(BigDecimal.ZERO) == 0);
	}

	@Test
	public void inter_bank_transfer_has_theoretical_limit_of_architecture() {
		Transferable transfer = new InterBankTransfer();

		assertTrue(transfer.getLimit().compareTo(new BigDecimal(Integer.MAX_VALUE)) == 0);
	}

}
