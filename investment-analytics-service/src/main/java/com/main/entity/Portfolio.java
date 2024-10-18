package com.main.entity;

import java.sql.Date;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Portfolio {
	@Id
	private int investmentId;
	private int userId;
	private String assetType;
	private int quantity;
	private double purchasePrice;
	private double currentPrice;
	private Date purchaseDate;
	@CreatedDate
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;
}
