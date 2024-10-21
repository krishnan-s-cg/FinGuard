package com.main;

import com.main.controller.TransactionController;
import com.main.dto.DebtTxn;
import com.main.dto.PortfolioDto;
import com.main.dto.TransactionRequest;
import com.main.entity.Transaction;
import com.main.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    private Transaction transaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transaction = new Transaction(); // Initialize with appropriate fields
    }

    @Test
    void createTransaction_shouldReturnCreatedTransaction() {
        when(transactionService.createTransaction(any(Transaction.class))).thenReturn(transaction);

        ResponseEntity<Transaction> response = transactionController.createTransaction(transaction);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(transaction, response.getBody());
    }

    @Test
    void getTransactionById_shouldReturnTransaction() {
        when(transactionService.getTransactionById(anyInt())).thenReturn(transaction);

        ResponseEntity<Transaction> response = transactionController.getTransactionById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transaction, response.getBody());
    }

    @Test
    void getTransactionsByUserId_shouldReturnTransactionList() {
        List<Transaction> transactions = Collections.singletonList(transaction);
        when(transactionService.getTransactionsByUserId(anyInt())).thenReturn(transactions);

        ResponseEntity<List<Transaction>> response = transactionController.getTransactionsByUserId(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactions, response.getBody());
    }

    @Test
    void makeTransaction_shouldReturnSuccessMessage() {
        TransactionRequest txnRequest = new TransactionRequest(); // Initialize as needed
        String expectedMessage = "Transaction successful";
        when(transactionService.makeTransaction(any(TransactionRequest.class))).thenReturn(expectedMessage);

        ResponseEntity<String> response = transactionController.makeTransaction(txnRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMessage, response.getBody());
    }

    @Test
    void portfolioTransaction_shouldReturnOk() {
        PortfolioDto portfolioDto = new PortfolioDto(); // Initialize as needed

        ResponseEntity<Void> response = transactionController.portfolioTransaction(portfolioDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(transactionService, times(1)).portfolioTransaction(portfolioDto);
    }

    @Test
    void debtTransaction_shouldReturnOk() {
        DebtTxn debtTxn = new DebtTxn(); // Initialize as needed

        ResponseEntity<Void> response = transactionController.debtTransaction(debtTxn);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(transactionService, times(1)).debtTransaction(debtTxn);
    }

    // Uncomment the tests for update and delete once implemented
    // @Test
    // void updateTransaction_shouldReturnUpdatedTransaction() { ... }
    
    // @Test
    // void deleteTransaction_shouldReturnNoContent() { ... }
}
