package com.main.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class DebtRequest {
	private int loanId;
	private int userId;
    private Double principalAmount;
    private Double interestRate;
    private Double emiAmount;
    private Date startDate;
    private Date endDate;
}
