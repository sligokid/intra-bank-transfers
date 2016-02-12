package com.sligokid.origin.digital.bank;

import java.math.BigDecimal;

public interface Transferable {

	boolean isSuccess();

	BigDecimal getLimit();

	BigDecimal getCommission();

}