package com.main.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class DebtRequest {
	private int userId; 
	private String loanType;
    private Double principalAmount;
    private Double interestRate;
    private Double emiAmount;
    private Double amountPaid;
    private Date startDate;
    private Date endDate;
}
