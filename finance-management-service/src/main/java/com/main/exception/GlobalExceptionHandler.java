package com.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler
{
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<Object> handleTransactionNotFoundException(TransactionNotFoundException ex, WebRequest request)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<Object> handleInsufficientBalanceException(InsufficientBalanceException ex, WebRequest request)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DebtCreateFailedException.class)
	public ResponseEntity<Object> handleDebtCreateFailedException(DebtCreateFailedException ex, WebRequest request)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DebtFetchFailedException.class)
	public ResponseEntity<Object> handleDebtFetchFailedException(DebtFetchFailedException ex, WebRequest request)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DebtUpdateFailedException.class)
	public ResponseEntity<Object> handleDebtUpdateFailedException(DebtUpdateFailedException ex, WebRequest request)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DebtDeleteFailedException.class)
	public ResponseEntity<Object> handleDebtDeleteFailedException(DebtDeleteFailedException ex, WebRequest request)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(TransactionCreateFailedException.class)
	public ResponseEntity<Object> handleTransactionCreateFailedException(TransactionCreateFailedException ex, WebRequest request)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(TransactionFetchFailedException.class)
	public ResponseEntity<Object> handleTransactionFetchFailedException(TransactionFetchFailedException ex, WebRequest request)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request)
	{
		return new ResponseEntity<>("An Error occured: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}