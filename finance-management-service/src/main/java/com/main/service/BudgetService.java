 package com.main.service;

import java.math.BigDecimal;
import java.util.List;



import com.main.dto.BudgetDto;
import com.main.dto.BudgetReportRequest;
import com.main.dto.BudgetResponse;
import com.main.entity.Budget;


public interface BudgetService {
//		List<Budget> getBudgetForUser(int userId);
	
		Budget createBudgetService(BudgetDto budgetDto);
	    Budget getBudgetByIdService(int budgetId);
	    Budget updateBudgetService(int budgetId, BudgetDto budgetDto);
	    BigDecimal getRemainingAmountService(int budgetId); 
	    void deleteBudgetService(int budgetId);
	    Budget getUserBudgetsService(int userId);
	    BudgetResponse getBudgetReport(BudgetReportRequest budgetReportRequest);
}