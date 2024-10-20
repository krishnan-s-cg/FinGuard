package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.BudgetDto;
import com.main.dto.DebtDto;
import com.main.dto.TransactionDto;
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
//        List<Transaction> transactions = transactionRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
//        if (transactions.isEmpty()) {
//            throw new ResourceNotFoundException("No transactions found for user ID: " + userId + " between " + startDate + " and " + endDate);
//        }
//        return transactions.stream()
//            .map(this::mapToIncomeExpenseReport)
//            .collect(Collectors.toList());
    	return financeClient.getTransactionsByUserId(userId);
    }

    @Override
    public BudgetDto getBudgetReport(int userId, LocalDate startDate, LocalDate endDate) {
    	return financeClient.getBudgetReport(userId, startDate, endDate);
//            if (budget == null) {
//                throw new ResourceNotFoundException("Budget not found for user ID: " + userId);
//            }
//            return mapToBudgetReport(budget); 
    }

	@Override
	public List<DebtDto> getDebtsReport(int userId) {
		List<DebtDto> debt = financeClient.getDebtsByUserId(userId);
		return debt;
	}

//        @Override
//        public List<Portfolio> getPortfolioReport(int userId) {
//            List<Portfolio> portfolios = portfolioRepository.findByUserId(userId);
//            if (portfolios.isEmpty()) {
//                throw new ResourceNotFoundException("No portfolios found for user ID: " + userId);
//            }
//            return mapToPortfolioPerformanceReport(portfolios);
//        }
//
//        @Override
//        public List<FinancialGoalReport> generateFinancialGoalsReport(Long userId) {
//            List<FinancialGoal> goals = goalRepository.findByUserId(userId);
//            if (goals.isEmpty()) {
//                throw new ResourceNotFoundException("No financial goals found for user ID: " + userId);
//            }
//            return goals.stream()
//                .map(this::mapToFinancialGoalReport)
//                .collect(Collectors.toList());
//        }
//
//        @Override
//        public List<HistoricalReport> fetchHistoricalReports(Long userId) {
//            List<HistoricalReport> reports = historicalReportRepository.findByUserId(userId);
//            if (reports.isEmpty()) {
//                throw new ResourceNotFoundException("No historical reports found for user ID: " + userId);
//            }
//            return reports;
//        }
}
        
       