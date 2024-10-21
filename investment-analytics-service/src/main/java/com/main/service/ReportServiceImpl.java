package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.BudgetDto;
import com.main.dto.DebtDto;
import com.main.dto.TransactionDto;
import com.main.exception.BudgetNotFoundException;
import com.main.exception.DebtNotFoundException;
import com.main.exception.TransactionNotFoundException;
import com.main.proxy.FinanceClient;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private FinanceClient financeClient;

    @Override
    public List<TransactionDto> generateIncomeExpenseReport(int userId) {

    	List<TransactionDto> transactions= financeClient.getTransactionsByUserId(userId);
    	if (transactions == null || transactions.isEmpty()) {
            throw new TransactionNotFoundException("No transactions found for user ID: " + userId);
        }
        return transactions;
    }

    @Override
    public BudgetDto getBudgetReport(int userId, LocalDate startDate, LocalDate endDate) {
    	BudgetDto budget = financeClient.getBudgetReport(userId, startDate, endDate);
    	if (budget == null) {
            throw new BudgetNotFoundException("No budget report found for user ID: " + userId);
        }
        return budget;
    }
    
	@Override
	public List<DebtDto> getDebtsReport(int userId) {
		List<DebtDto> debt = financeClient.getDebtsByUserId(userId);
		 if (debt == null || debt.isEmpty()) {
		        throw new DebtNotFoundException("No debts found for user ID: " + userId);
		    }
		    return debt;
	}


}
        
       