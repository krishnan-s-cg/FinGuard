package com.main.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PortfolioResponse {

	private int investmentId;
    private int userId;
    private String assetType;
    private int quantity;
    private double purchasePrice;
    private double currentPrice;
    private Date purchaseDate;
    private Date createdAt;
    private Date updatedAt;
}
