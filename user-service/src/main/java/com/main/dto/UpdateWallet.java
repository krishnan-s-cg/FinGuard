package com.main.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import lombok.Data;

@Data
public class UpdateWallet {
	
	@Digits(integer= 6, fraction= 2)
	private BigDecimal amount;

}
