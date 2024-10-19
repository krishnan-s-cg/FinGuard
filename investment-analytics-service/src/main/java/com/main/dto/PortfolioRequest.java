package com.main.dto;


//import java.sql.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class PortfolioRequest {
	private int userId;
    private String assetType;
    private int quantity;
    private double purchasePrice;
    private double currentPrice;
    private LocalDate purchaseDate;
//    private boolean isMutualFund;
//    private int investmentDuration;
	
}
