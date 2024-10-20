package com.main.exception;

public class CustomException extends RuntimeException {

	public class UserNotFoundException extends RuntimeException {
	    public UserNotFoundException(String message) {
	        super(message);
	    }
	}
	public class TransactionNotFoundException extends RuntimeException {
	    public TransactionNotFoundException(String message) {
	        super(message);
	    }
	}

	public class BudgetNotFoundException extends RuntimeException {
	    public BudgetNotFoundException(String message) {
	        super(message);
	    }
	}

	public class DebtNotFoundException extends RuntimeException {
	    public DebtNotFoundException(String message) {
	        super(message);
	    }
	    
}
}