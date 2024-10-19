package com.main.entity;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Portfolio {
	@Id
	@GeneratedValue
	private int investmentId;
    @NotNull(message = "User  ID cannot be null")
	private int userId;
    @NotBlank(message = "Asset type cannot be blank")
	private String assetType;
    @Min(value = 0, message = "Quantity must be non-negative")
	private int quantity;
    @Min(value = 0, message = "Purchase price must be non-negative")
	private double purchasePrice;
    @Min(value = 0, message = "Current price must be non-negative")
	private double currentPrice;
	private LocalDate purchaseDate;
	@CreationTimestamp
	private LocalDate createdAt;
	@UpdateTimestamp
	private LocalDate updatedAt;

}



