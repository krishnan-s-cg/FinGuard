package com.main.entity;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Budget {
	
	@Id
	@GeneratedValue
	private int budgetId;
	private int userId;
	private String category;
	private double amount;
	private double currency;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	

}
