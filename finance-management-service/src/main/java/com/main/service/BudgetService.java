 package com.main.service;

import java.util.List;



import com.main.dto.BudgetDto;
import com.main.entity.Budget;


public interface BudgetService {
		List<Budget> getBudgetForUser(int userId);
		Budget createBudgetService(BudgetDto budgetDto);
	    Budget getBudgetByIdService(int budgetId);
	    Budget updateBudgetService(int budgetId, BudgetDto budgetDto);
	    void deleteBudgetService(int budgetId);
	    List<Budget> getUserBudgetsService(int userId);
}
