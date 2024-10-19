package com.main.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BudgetDto {
	    private int userId;
	    private double amount;
	    private String category;
	    private LocalDate startDate;
	    private LocalDate endDate;
	
	}


