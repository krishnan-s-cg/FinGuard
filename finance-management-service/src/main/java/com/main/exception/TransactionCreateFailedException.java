package com.main.exception;

public class TransactionCreateFailedException extends RuntimeException{
	public TransactionCreateFailedException(String msg) {
		super(msg);
	}
}
