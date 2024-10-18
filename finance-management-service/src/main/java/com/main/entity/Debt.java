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
public class Debt {
    @Id
    @GeneratedValue
    private int loanId;
    
    @ManyToOne
    private User user;
    
    private String loanType;
    private double principalAmount;
    private double interestRate;
    private double emiAmount;
    private Date startDate;
    private Date endDate;     
    private Date createdAt;
    private Date updatedAt;
}
