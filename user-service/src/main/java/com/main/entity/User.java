package com.main.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "userProfile")
public class User 
{
	@Id
	@GeneratedValue
	private int userId;
	private String userName;
	private String email;
	private String role;
	
	private LocalDate createdAt;
	private LocalDate updatedAt;
}
