package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.main.dto.DebtRequest;
import com.main.dto.DebtTxn;
import com.main.dto.TransactionRequest;
import com.main.entity.Transaction;
import com.main.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

//    @PostMapping
//    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
//        Transaction createdTransaction = transactionService.createTransaction(transaction);
//        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable int id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Transaction> updateTransaction(@PathVariable int id, @RequestBody Transaction transaction) {
//        Transaction updatedTransaction = transactionService.updateTransaction(id, transaction);
//        if (updatedTransaction != null) {
//            return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Boolean> deleteTransaction(@PathVariable int id) {
//        boolean isDeleted = transactionService.deleteTransaction(id);
//        if (isDeleted) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionsByUserId(@PathVariable int userId) {
        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

//    @PostMapping("/user/txn/{senderUserId}/{receiverUserId}/{amount}")
//    public ResponseEntity<Void> makeTransaction(@PathVariable int senderUserId, @PathVariable int receiverUserId, @PathVariable double amount) {
//        transactionService.makeTransaction(senderUserId, receiverUserId, amount);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
    @PostMapping("/user/txn")
    public ResponseEntity<String> makeTransaction(@RequestBody TransactionRequest txn) {
    	String msg = transactionService.makeTransaction(txn);
    	return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
    @PostMapping("/user/debt")
    public ResponseEntity<Void> debtTransaction(@RequestBody DebtTxn debtTxn){
    	transactionService.debtTransaction(debtTxn);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
}
