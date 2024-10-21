package com.main.dto;


import java.math.BigDecimal;
//import java.sql.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class PortfolioRequest {
	public PortfolioRequest() {
		// TODO Auto-generated constructor stub
	}
	private int userId;
    private String assetType;
    private int quantity;
    private BigDecimal purchasePrice;
    private BigDecimal currentPrice;
    private LocalDate purchaseDate;
//    private boolean isMutualFund;
//    private int investmentDuration;
	
}
