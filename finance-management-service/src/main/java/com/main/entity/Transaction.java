package com.main.entity;

import java.sql.Date;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Transaction {
	@Id
	@GeneratedValue
	private int txnId;
	private int userId;
	private String type;
	private String category;
	private double amount;
	private double currency;
	private double balance;
	private Date txnDate;
	private String description;
	@CreatedDate
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;
	
}
