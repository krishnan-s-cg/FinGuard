package com.main.service;

import java.util.List;

import com.main.entity.Transaction;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction, int userId);
    Transaction getTransactionById(int id);
    Transaction updateTransaction(int id, Transaction transaction);
    void deleteTransaction(int id);
    List<Transaction> getTransactionsByUserId(int userId);
}

