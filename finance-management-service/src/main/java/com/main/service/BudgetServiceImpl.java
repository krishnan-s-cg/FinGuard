package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.BudgetDto;
import com.main.dto.UserDto;
import com.main.entity.Budget;
import com.main.proxy.UserClient;
import com.main.repository.BudgetRepository;

@Service
public class BudgetServiceImpl implements BudgetService{
	
	@Autowired
	private BudgetRepository budgetRepository;

	@Autowired
	private UserClient userClient;
//	@Autowired
//	private BudgetService budgetService;

    @Override
    public Budget createBudgetService(BudgetDto budgetDto) {
        Budget budget = new Budget();
        budget.setUserId(budgetDto.getUserId());
        budget.setAmount(budgetDto.getAmount());
        budget.setCategory(budgetDto.getCategory());
        budget.setStartDate(budgetDto.getStartDate());
        budget.setEndDate(budgetDto.getEndDate());
        return budgetRepository.save(budget);
    }

    @Override
    public Budget getBudgetByIdService(int budgetId) {
        return budgetRepository.findById(budgetId)
                .orElseThrow(() -> new RuntimeException("Budget not found with ID: " + budgetId));
    }

    @Override
    public Budget updateBudgetService(int budgetId, BudgetDto budgetDto) {
        Budget budget = getBudgetByIdService(budgetId);  // Fetch the existing budget
        budget.setAmount(budgetDto.getAmount());
        budget.setCategory(budgetDto.getCategory());
        budget.setStartDate(budgetDto.getStartDate());
        budget.setEndDate(budgetDto.getEndDate());
        return budgetRepository.save(budget);
    }

    @Override
    public void deleteBudgetService(int budgetId) {
        Budget budget = getBudgetByIdService(budgetId);
        budgetRepository.delete(budget);
    }

    @Override
    public List<Budget> getUserBudgetsService(int userId) {
        return budgetRepository.findByUserId(userId);
    }

	@Override
	public List<Budget> getBudgetForUser(int userId) {		
		List<Budget> budget = budgetRepository.findByUserId(userId);
		return budget;
	}
	

}
