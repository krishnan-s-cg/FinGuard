package com.main.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Report {
@Id
public String reportType;
public LocalDate reportDate;
public LocalDate generatedAt;

}
