package com.main.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class BudgetDto {

	    private int userId;
	    private BigDecimal amount;
	    private BigDecimal spentAmount;
	    private String category;
	    private LocalDate startDate;
	    private LocalDate endDate;
	
	

}



