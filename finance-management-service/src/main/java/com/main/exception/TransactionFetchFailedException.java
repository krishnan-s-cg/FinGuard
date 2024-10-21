package com.main.exception;

public class TransactionFetchFailedException extends RuntimeException {
	public TransactionFetchFailedException(String msg) {
		super(msg);
	}
}
