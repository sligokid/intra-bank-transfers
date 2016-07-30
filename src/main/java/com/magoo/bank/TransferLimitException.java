package com.magoo.bank;

public class TransferLimitException extends Exception {

	private static final long serialVersionUID = 4221426146061466732L;

	public TransferLimitException(String string) {
		super(string);
	}

}
