package com.main.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionRequest {
	private int userId;
	private double amount;
	private BigDecimal wallet;
}
