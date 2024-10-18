package com.main.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.User;
import com.main.entity.Transaction;
import com.main.proxy.UserClient;
import com.main.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserClient userClient;

//    @Override
//    public Transaction createTransaction(Transaction transaction) {
//        logger.info("Creating transaction: {}", transaction);
//        return transactionRepository.save(transaction);
//    }

    @Override
    public Transaction getTransactionById(int id) {
        logger.info("Fetching transaction with ID: {}", id);
        return transactionRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Transaction with ID {} not found", id);
                    return new RuntimeException("Transaction not found");
                });
    }

//    @Override
//    public Transaction updateTransaction(int txnId, Transaction transaction) {
//        logger.info("Updating transaction with ID: {}", txnId);
//        Transaction existingTransaction = transactionRepository.findById(txnId)
//                .orElseThrow(() -> {
//                    logger.error("Transaction with ID {} not found", txnId);
//                    return new RuntimeException("Transaction not found");
//                });
//
//        existingTransaction.setUserId(transaction.getUserId());
//        existingTransaction.setAmount(transaction.getAmount());
//        existingTransaction.setType(transaction.getType());
//        existingTransaction.setCategory(transaction.getCategory());
//        existingTransaction.setDescription(transaction.getDescription());
//        existingTransaction.setTxnDate(transaction.getTxnDate());
//
//        return transactionRepository.save(existingTransaction);
//    }

//    @Override
//    public Boolean deleteTransaction(int id) {
//        logger.info("Deleting transaction with ID: {}", id);
//        if (transactionRepository.existsById(id)) {
//            transactionRepository.deleteById(id);
//            logger.info("Transaction with ID {} deleted successfully", id);
//            return true;
//        } else {
//            logger.error("Transaction with ID {} not found", id);
//            return false;
//        }
//    }

    @Override
    public List<Transaction> getTransactionsByUserId(int userId) {
        logger.info("Fetching transactions for user ID: {}", userId);
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        if (transactions == null || transactions.isEmpty()) {
            logger.warn("No transactions found for user ID: {}", userId);
        }
        return transactions;
    }

    @Override
    public void makeTransaction(int senderUserId, int receiverUserId, double amount) {
        logger.info("Making transaction from user ID: {} to user ID: {} with amount: {}", senderUserId, receiverUserId, amount);
        User receiver = userClient.getUserById(receiverUserId);
        User sender = userClient.getUserById(senderUserId);

        if (receiver == null) {
            logger.error("Receiver user with ID {} not found", receiverUserId);
            throw new RuntimeException("Receiver user not found");
        }
        if (sender == null) {
            logger.error("Sender user with ID {} not found", senderUserId);
            throw new RuntimeException("Sender user not found");
        }
        if (sender.getBalance() < amount) {
            logger.error("Insufficient balance for user ID {}", senderUserId);
            throw new RuntimeException("Insufficient Balance");
        }

        receiver.setBalance(receiver.getBalance() + amount);
        sender.setBalance(sender.getBalance() - amount);

        userClient.updateUser(receiverUserId, receiver);
        userClient.updateUser(senderUserId, sender);

        logger.info("Transaction completed successfully from user ID: {} to user ID: {} with amount: {}", senderUserId, receiverUserId, amount);
    }
}
