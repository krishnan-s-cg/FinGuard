package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.main.entity.Transaction;
import com.main.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction, @RequestParam int userId) {
        return transactionService.createTransaction(transaction, userId);
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable int id) {
        return transactionService.getTransactionById(id);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable int id, @RequestBody Transaction transaction) {
        return transactionService.updateTransaction(id, transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable int id) {
        transactionService.deleteTransaction(id);
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getTransactionsByUserId(@PathVariable int userId) {
        return transactionService.getTransactionsByUserId(userId);
    }
}
