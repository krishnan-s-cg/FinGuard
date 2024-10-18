package com.main.dto;


import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioRequest {
	private int userId;
    private String assetType;
    private int quantity;
    private double purchasePrice;
    private double currentPrice;
    private Date purchaseDate;
}
