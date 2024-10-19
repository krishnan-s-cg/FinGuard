package com.main.entity;

import java.sql.Date;

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
public class Debt {
    @Id
    @GeneratedValue
    private int loanId;
    private int userId;    
    private String loanType;
    private double principalAmount;
    private double interestRate;
    private double emiAmount;
    private Date startDate;
    private Date endDate;     
	@CreatedDate
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;
}
