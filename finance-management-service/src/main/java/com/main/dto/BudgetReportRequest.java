package com.main.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BudgetReportRequest {
	private int userId;
	private LocalDate startDate;
	private LocalDate endDate;
}
