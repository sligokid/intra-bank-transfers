package com.sligokid.origin.digital.bank;

import java.math.BigDecimal;

public class InterBankTransfer implements Transferable {

	private BigDecimal COMMISSION = BigDecimal.ZERO;

	private BigDecimal LIMIT = new BigDecimal(Integer.MAX_VALUE); // FIXME

	private boolean success = true;

	@Override
	public BigDecimal getCommission() {
		return COMMISSION;
	}

	@Override
	public BigDecimal getLimit() {
		return LIMIT;
	}

	@Override
	public boolean isSuccess() {
		return success;
	}

}
