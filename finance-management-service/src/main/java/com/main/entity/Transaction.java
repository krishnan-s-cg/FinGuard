package com.main.entity;

import java.sql.Date;

import com.main.dto.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	private Date txnDate;
	private String description;
	
	private Date createdAt;
	private Date updatedAt;
	
}
