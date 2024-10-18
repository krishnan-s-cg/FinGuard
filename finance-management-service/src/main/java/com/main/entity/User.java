package com.main.entity;
 
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
 
@Entity
@Data
public class User {   
	@Id
	private int userId;     
	private String username;     
	private String email; 
	private String role; 
}
