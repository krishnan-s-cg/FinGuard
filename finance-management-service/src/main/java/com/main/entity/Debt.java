package com.main.entity;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Entity
@Data
public class Debt {
    @Id
    @GeneratedValue
    private int loanId; 

    @NotNull(message = "User ID cannot be null")
    private int userId;
    
    @NotNull(message = "LoanType cannot be null")
    private String loanType; 

    @NotNull(message = "Principal amount cannot be null")
    private double principalAmount;

    @NotNull(message = "Interest rate cannot be null")
    private double interestRate;

    @NotNull(message = "EMI amount cannot be null")
    private double emiAmount;

    @NotNull(message = "Amount paid cannot be null")
    private double amountPaid;

    @NotNull(message = "Start date cannot be null")
//    @PastOrPresent(message = "Start date cannot be in the future")
    private Date startDate;

    @NotNull(message = "End date cannot be null")
//    @PastOrPresent(message = "End date cannot be in the future")
    private Date endDate;

    @CreationTimestamp
//    @PastOrPresent(message = "Creation date cannot be in the future")
    private Date createdAt;

    @UpdateTimestamp
//    @PastOrPresent(message = "Update date cannot be in the future")
    private Date updatedAt;
}