package com.main.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
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
public class Goal {
	@Id
	@GeneratedValue
	private int goalId;
	@NotNull(message="user ID cannot be null")
	private int userId;
	@NotNull(message="Goal name cannot be null")
//	@Pattern(regexp="^[A-Za-z]$",message="Goal name must be Alphabetic")
	private String goalName;
	@NotNull(message="Target amount cannot be null")
//	@Positive(message ="Target amount must be a posistive number")
	private double targetAmount;
	@NotNull(message="Current amount cannot be null")
//	@Positive(message ="Current amount must be a posistive number")
	private double currentAmount;
	@NotNull(message="Target date cannot be null")
	private LocalDate targetDate;
	@CreationTimestamp
	private LocalDate createdAt;
	@UpdateTimestamp
	private LocalDate updatedAt;
}
