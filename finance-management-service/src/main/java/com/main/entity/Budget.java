package com.main.entity;

import java.time.LocalDate;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.main.dto.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class Budget {
	@Id
	@GeneratedValue
	private int budgetId;
	@NotNull(message="user ID cannot be null")
	private int userId;
	@NotNull(message="Catagory cannot be null")
	@Pattern(regexp="^[A-Za-z]$",message="Category must be Alphabetic")
	private String category;
	@NotNull(message="Amount cannot be null")
	@Positive(message ="Amount must be a posistive number")
	private double amount;
	@NotNull(message="Amount cannot be null")
	@Positive(message ="Amount must be a posistive number")
	private double Spentamount;
	@NotNull(message="Start date cannot be null")
	private LocalDate startDate;
	@NotNull(message=" End date cannot be null")
	private LocalDate endDate;
	@CreatedDate
	private LocalDate createdAt;
	@UpdateTimestamp
	private LocalDate updatedAt;
}


