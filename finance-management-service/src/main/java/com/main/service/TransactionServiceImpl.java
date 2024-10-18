package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.User;
import com.main.entity.Transaction;
import com.main.proxy.UserClient;
import com.main.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserClient userClient;

    @Override
    public Transaction createTransaction(Transaction transaction, int userId) {
        User user = userClient.getUserById(userId);
        transaction.setUser(user);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(int id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    @Override
    public Transaction updateTransaction(int id, Transaction transaction) {
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        User user = userClient.getUserById(transaction.getUser().getUserId());
        existingTransaction.setUser(user);
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setType(transaction.getType());
        existingTransaction.setCategory(transaction.getCategory());
        existingTransaction.setDescription(transaction.getDescription());
        existingTransaction.setTxnDate(transaction.getTxnDate());

        return transactionRepository.save(existingTransaction);
    }

    @Override
    public void deleteTransaction(int id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public List<Transaction> getTransactionsByUserId(int userId) {
        User user = userClient.getUserById(userId);
        return transactionRepository.findByUser(user);
    }
}
