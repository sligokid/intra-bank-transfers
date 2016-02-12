package com.sligokid.origin.digital.bank;

import java.math.BigDecimal;

public class IntraBankTransfer implements Transferable {

	private BigDecimal COMMISSION = new BigDecimal("5");

	private BigDecimal LIMIT = new BigDecimal("1000");

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
