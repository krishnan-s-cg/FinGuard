package com.main.exception;

public class DebtNotFoundException extends RuntimeException {
	public DebtNotFoundException(String msg) {
		super(msg);
	}
}
