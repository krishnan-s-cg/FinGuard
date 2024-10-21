package com.main.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class BudgetDto {
<<<<<<< HEAD
	    private int userId;
	    private double amount;
	    private double spentAmount;
	    private String category;
	    private LocalDate startDate;
	    private LocalDate endDate;
	
	}
=======
    private int userId;
    private BigDecimal amount;
    private String category;
    private LocalDate startDate;
    private LocalDate endDate;
}
>>>>>>> 17cad5cf84f9f65020aab699eae01d8121fb199d


