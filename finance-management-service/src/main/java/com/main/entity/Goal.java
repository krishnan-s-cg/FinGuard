package com.main.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Goal {
	@Id
	@GeneratedValue
	private int goalId;
	private int userId;
	private String goalName;
	private double targetAmount;
	private double currentAmount;
	private LocalDate targetDate;
	private LocalDate createdAt;
	private LocalDate updatedAt;
}
