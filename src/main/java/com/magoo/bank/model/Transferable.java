package com.magoo.bank.model;

import java.math.BigDecimal;

public interface Transferable {

	boolean isSuccess();

	BigDecimal getLimit();

	BigDecimal getCommission();

	Account getReciever();

	void setReciever(Account reciever);

	BigDecimal getAmount();

	void setAmount(BigDecimal amount);

	void setPayee(Account account);

}