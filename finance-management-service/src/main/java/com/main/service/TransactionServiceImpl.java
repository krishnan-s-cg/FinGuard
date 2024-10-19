package com.main.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.DebtRequest;
import com.main.dto.DebtTxn;
import com.main.dto.TransactionRequest;
import com.main.dto.User;
import com.main.entity.Debt;
import com.main.entity.Transaction;
import com.main.exception.DebtNotFoundException;
import com.main.exception.DebtUpdateFailedException;
import com.main.exception.InsufficientBalanceException;
import com.main.exception.TransactionCreateFailedException;
import com.main.exception.TransactionFetchFailedException;
import com.main.exception.TransactionNotFoundException;
import com.main.exception.UserNotFoundException;
import com.main.proxy.UserClient;
import com.main.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserClient userClient;
    
    @Autowired
    private DebtService debtService;

    @Override
    public Transaction getTransactionById(int id) {
        logger.info("Fetching transaction with ID: {}", id);
        return transactionRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Transaction with ID {} not found", id);
                    return new TransactionNotFoundException("Transaction not found");
                });
    }

    @Override
    public List<Transaction> getTransactionsByUserId(int userId) {
        logger.info("Fetching transactions for user ID: {}", userId);
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        if (transactions == null || transactions.isEmpty()) {
            logger.warn("No transactions found for user ID: {}", userId);
            throw new TransactionNotFoundException("Transaction not found for the userId: " + userId);
        }
        return transactions;
    }
    
    @Override
    public String makeTransaction(TransactionRequest txn) {
        logger.info("Making transaction from user ID: {} to user ID: {} with amount: {}", txn.getSenderUserId(), txn.getReceiverUserId(), txn.getAmount());
        
        User receiver = userClient.getUserById(txn.getReceiverUserId());
        if (receiver == null) {
            logger.error("Receiver user with ID {} not found", txn.getReceiverUserId());
            throw new UserNotFoundException("Receiver user not found");
        }
        
        User sender = userClient.getUserById(txn.getSenderUserId());
        if (sender == null) {
            logger.error("Sender user with ID {} not found", txn.getSenderUserId());
            throw new UserNotFoundException("Sender user not found");
        }
        
        BigDecimal amountBigDecimal = BigDecimal.valueOf(txn.getAmount());
        
        if (sender.getWallet().compareTo(amountBigDecimal) < 0) {
            logger.error("Insufficient balance for user ID {}", txn.getSenderUserId());
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        
        receiver.setWallet(receiver.getWallet().add(amountBigDecimal));
        sender.setWallet(sender.getWallet().subtract(amountBigDecimal));
        
        userClient.updateUser(txn.getReceiverUserId(), receiver);
        Transaction receiverTxn = new Transaction();
        receiverTxn.setUserId(txn.getReceiverUserId());
        receiverTxn.setAmount(amountBigDecimal.doubleValue());
        receiverTxn.setWallet(receiver.getWallet());
        logger.info("receiver txn: {}", receiverTxn); 
        transactionRepository.save(receiverTxn);
        
        userClient.updateUser(txn.getSenderUserId(), sender);
        Transaction senderTxn = new Transaction();
        senderTxn.setUserId(txn.getSenderUserId());
        senderTxn.setAmount(-amountBigDecimal.doubleValue());
        senderTxn.setWallet(sender.getWallet());
        logger.info("sender txn: {}", senderTxn);
        transactionRepository.save(senderTxn);
        
        logger.info("Transaction completed successfully from user ID: {} to user ID: {} with amount: {}", txn.getSenderUserId(), txn.getReceiverUserId(), txn.getAmount());
        return "Transaction successful!!";
    }

    @Override
    public void debtTransaction(DebtTxn debtTxn) {
        logger.info("Making Transaction to the Debt Service");
        
        Debt debt = debtService.getDebtById(debtTxn.getLoanId());
        if (debt == null) {
            logger.error("Debt not found for Loan ID: {}", debtTxn.getLoanId());
            throw new DebtNotFoundException("Debt not found");
        }

        User user = userClient.getUserById(debt.getUserId());
        if (user == null) {
            logger.error("User not found for User ID: {}", debt.getUserId());
            throw new UserNotFoundException("User not found");
        }

        BigDecimal amountBigDecimal = BigDecimal.valueOf(debtTxn.getAmount());
        if (user.getWallet().compareTo(amountBigDecimal) < 0) {
            logger.error("Insufficient balance for user ID {}", debt.getUserId());
            throw new InsufficientBalanceException("Insufficient Balance");
        }

        debtService.updateDebt(debtTxn.getLoanId(), debtTxn.getAmount());
        user.setWallet(user.getWallet().subtract(amountBigDecimal));
        userClient.updateUser(user.getUserId(), user);
        
        logger.info("Debt transaction completed successfully for Loan ID: {}", debtTxn.getLoanId());

    } 
}
