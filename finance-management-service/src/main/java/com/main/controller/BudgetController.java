package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.BudgetDto;
import com.main.entity.Budget;
import com.main.service.BudgetService;

           //@Controller + @ResponseBody   handles HTTP requests in a RESTful web service, such as GET, POST, PUT, DELETE, etc.,
                                                        //  and will send back responses in formats like JSON or XML (typically JSON).
@RestController
@RequestMapping("/api/budgets")                     // defines a base URL path for the controller or method. 
public class BudgetController {
	
	

	    @Autowired   // Automatically injects the BudgetService bean into this class.
	    private BudgetService budgetService;

	    // Create a new budget
	    @PostMapping
	    public ResponseEntity<Budget> createBudget(@RequestBody BudgetDto budgetDto) {
	        Budget createdBudget = budgetService.createBudgetService(budgetDto);
	        return new ResponseEntity<>(createdBudget, HttpStatus.CREATED);
	    }

	    // Get a budget by ID
	    @GetMapping("/{budgetId}")
	    public ResponseEntity<Budget> getBudgetById(@PathVariable int budgetId) {
	        Budget budget = budgetService.getBudgetByIdService(budgetId);
	        return new ResponseEntity<>(budget, HttpStatus.OK);
	    }

	    // Update a budget
	    @PutMapping("/{budgetId}")
	    public ResponseEntity<Budget> updateBudget(@PathVariable int budgetId, @RequestBody BudgetDto budgetDto) {
	        Budget updatedBudget = budgetService.updateBudgetService(budgetId, budgetDto);
	        return new ResponseEntity<>(updatedBudget, HttpStatus.OK);
	    }

	    // Delete a budget
	    @DeleteMapping("/{budgetId}")
	    public ResponseEntity<Void> deleteBudget(@PathVariable int budgetId) {
	        budgetService.deleteBudgetService(budgetId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    // Get all budgets for a user
	    @GetMapping("/user/{userId}")
	    public ResponseEntity<List<Budget>> getUserBudgets(@PathVariable int  userId) {
	        List<Budget> budgets = budgetService.getUserBudgetsService(userId);
	        return new ResponseEntity<>(budgets, HttpStatus.OK);
	    }

}
