package com.main.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue
    private int txnId;

    @NotNull(message = "User ID cannot be null")
    @Min(value = 1, message = "User ID must be greater than or equal to 1")
    private int userId;

    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;
    
    @NotNull(message = "Wallet cannot be null")
    private BigDecimal wallet;
    
    private String txnType;

    @CreationTimestamp
    @PastOrPresent(message = "Transaction date cannot be in the future")
    private LocalDate txnDate;

    @CreationTimestamp
    @PastOrPresent(message = "Creation date cannot be in the future")
    private LocalDate createdAt;

    @UpdateTimestamp
    @PastOrPresent(message = "Update date cannot be in the future")
    private LocalDate updatedAt;
}