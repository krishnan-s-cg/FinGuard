package com.main.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class TransactionRequest {
	private int userId;
	private String type;
	private String category;
	private double amount;
	private double currency;
	private double balance;
	private Date txnDate;
	private String description;
}
