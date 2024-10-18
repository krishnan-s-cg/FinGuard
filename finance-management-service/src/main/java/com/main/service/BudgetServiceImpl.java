package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.BudgetDto;
import com.main.entity.Budget;
import com.main.repository.BudgetRepository;

@Service
public class BudgetServiceImpl implements BudgetService{
	
	@Autowired
	private BudgetRepository budRepo;


    @Override
    public Budget createBudget(BudgetDto budgetDto) {
        Budget budget = new Budget();
        budget.setUser(budgetDto.getUserId());
        budget.setAmount(budgetDto.getAmount());
        budget.setCategory(budgetDto.getCategory());
        budget.setStartDate(budgetDto.getStartDate());
        budget.setEndDate(budgetDto.getEndDate());
        return budRepo.save(budget);
    }

    @Override
    public Budget getBudgetById(int budgetId) {
        return budRepo.findById(budgetId)
                .orElseThrow(() -> new RuntimeException("Budget not found with ID: " + budgetId));
    }

    @Override
    public Budget updateBudget(int budgetId, BudgetDto budgetDto) {
        Budget budget = getBudgetById(budgetId);  // Fetch the existing budget
        budget.setAmount(budgetDto.getAmount());
        budget.setCategory(budgetDto.getCategory());
        budget.setStartDate(budgetDto.getStartDate());
        budget.setEndDate(budgetDto.getEndDate());
        return budRepo.save(budget);
    }

    @Override
    public void deleteBudget(int budgetId) {
        Budget budget = getBudgetById(budgetId);
        budRepo.delete(budget);
    }

    @Override
    public List<Budget> getUserBudgets(int userId) {
        return budRepo.findByUserId(userId);
    }
	

}
