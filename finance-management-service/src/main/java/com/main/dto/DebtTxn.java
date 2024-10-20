package com.main.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DebtTxn {
	private int loanId;
	private BigDecimal amount;
}
