package com.main.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Data
@Table(name = "userProfile")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int userId;
	
	private String userName;
	
	private String password;

	private String email;
	
	private String role;
	
<<<<<<< HEAD
=======
	@PositiveOrZero
	@Digits(integer = 6, fraction = 2)
	private BigDecimal wallet = BigDecimal.ZERO;
	
	@CreationTimestamp
	@PastOrPresent
>>>>>>> dd3b1393778953478ed028104ba1f5959e2f750d
	private LocalDate createdAt;
	
	@UpdateTimestamp
	private LocalDate updatedAt;
	
}
