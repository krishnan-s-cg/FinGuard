package com.main.service;
import java.util.List;  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.BudgetDto;
import com.main.entity.Budget;
import com.main.exception.BudgetNotFoundException; // Import the custom exception
import com.main.exception.InvalidBudgetException; // Import the custom exception
import com.main.exception.RemainingAmountException;
import com.main.proxy.UserClient;
import com.main.repository.BudgetRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BudgetServiceImpl implements BudgetService {

    private static final Logger logger = LoggerFactory.getLogger(BudgetServiceImpl.class);

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserClient userClient;

    @Override
    public Budget createBudgetService(BudgetDto budgetDto) {
        logger.info("Creating new budget for user ID: {}", budgetDto.getUserId());

        if (budgetDto.getAmount() <= 0) {
            logger.error("Invalid budget amount: {}", budgetDto.getAmount());
            throw new InvalidBudgetException("Budget amount must be positive.");
        }

        Budget budget = new Budget();
        budget.setUserId(budgetDto.getUserId());
        budget.setAmount(budgetDto.getAmount());
        budget.setSpentAmount(budgetDto.getSpentAmount());
        budget.setCategory(budgetDto.getCategory());
        budget.setStartDate(budgetDto.getStartDate());
        budget.setEndDate(budgetDto.getEndDate());

        logger.info("Budget created successfully for user ID: {}", budgetDto.getUserId());
        return budgetRepository.save(budget);
    }

    @Override
    public Budget getBudgetByIdService(int budgetId) {
        logger.info("Fetching budget with ID: {}", budgetId);
        return budgetRepository.findById(budgetId)
                .orElseThrow(() -> {
                    logger.error("Budget not found with ID: {}", budgetId);
                    return new BudgetNotFoundException("Budget not found with ID: " + budgetId);
                });
    }

    @Override
    public Budget updateBudgetService(int budgetId, BudgetDto budgetDto) {
        logger.info("Updating budget with ID: {}", budgetId);

        if (budgetDto.getAmount() <= 0) {
            logger.error("Invalid budget amount: {}", budgetDto.getAmount());
            throw new InvalidBudgetException("Budget amount must be positive.");
        }

        Budget budget = getBudgetByIdService(budgetId); // Fetch the existing budget
        budget.setAmount(budgetDto.getAmount());
        budget.setCategory(budgetDto.getCategory());
        budget.setStartDate(budgetDto.getStartDate());
        budget.setEndDate(budgetDto.getEndDate());

        logger.info("Budget updated successfully for ID: {}", budgetId);
        return budgetRepository.save(budget);
    }

    @Override
    public void deleteBudgetService(int budgetId) {
        logger.info("Deleting budget with ID: {}", budgetId);
        Budget budget = getBudgetByIdService(budgetId);
        budgetRepository.delete(budget);
        logger.info("Budget deleted successfully for ID: {}", budgetId);
    }

    @Override
    public List<Budget> getUserBudgetsService(int userId) {
        logger.info("Fetching budgets for user ID: {}", userId);
        return budgetRepository.findByUserId(userId);
    }
   
    public double getRemainingAmountService(int budgetId) {
//        logger.info("Calculating remaining amount for budget ID: {}", budgetId);
//        Budget budget = getBudgetByIdService(budgetId); // Fetch the budget
//        double remainingAmount = budget.getAmount() - budget.getSpentamount();
//        logger.info("Remaining amount for budget ID {}: {}", budgetId, remainingAmount);
//        return remainingAmount;
        logger.info("Calculating remaining amount for budget ID: {}", budgetId);
        Budget budget = getBudgetByIdService(budgetId);
        
        // Check if spent amount exceeds total budget
        if (budget.getSpentAmount() > budget.getAmount()) {
            logger.error("Spent amount exceeds the budget for budget ID: {}", budgetId);
            throw new RemainingAmountException("Spent amount exceeds the budget.");
        }
        double remainingAmount = budget.getAmount() - budget.getSpentAmount();
        logger.info("Remaining amount for budget ID {}: {}", budgetId, remainingAmount);
        return remainingAmount;
    }
}

