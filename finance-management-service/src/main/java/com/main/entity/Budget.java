package com.main.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.main.dto.User;

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
	@NotNull(message="user ID cannot be null")
	private int userId;
	@NotNull(message="Catagory cannot be null")
	private String category;
	@NotNull(message="Amount cannot be null")
//	@Positive(message ="Amount must be a posistive number")
	@PositiveOrZero
	@Digits(integer = 6, fraction = 2)
	private BigDecimal amount;
//	@NotNull(message="Amount cannot be null")
//	@Positive(message ="Amount must be a posistive number")
	@PositiveOrZero
	@Digits(integer = 6, fraction = 2)
	private BigDecimal spentAmount = BigDecimal.ZERO;
	@NotNull(message="Start date cannot be null")
	private LocalDate startDate;
	@NotNull(message=" End date cannot be null")
	private LocalDate endDate;
	@CreationTimestamp
	private LocalDate createdAt;
	@UpdateTimestamp
	private LocalDate updatedAt;
	}



