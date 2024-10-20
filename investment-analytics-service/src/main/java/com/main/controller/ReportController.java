package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.main.dto.BudgetDto;
import com.main.dto.DebtDto;
import com.main.dto.TransactionDto;
import com.main.entity.Portfolio;
import com.main.proxy.FinanceClient;
import com.main.service.PortfolioService;
import com.main.service.ReportService;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;
    
    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/financereport/{userId}")
    public ResponseEntity<List<DebtDto>> getDebtsReport(@PathVariable int userId) {
    	List<DebtDto> reports = reportService.getDebtsReport(userId);
        if (reports != null) {
            return new ResponseEntity<>(reports, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
    }

    @GetMapping("/incomexpensereport/{userId}")
    public ResponseEntity<List<TransactionDto>> getIncomeExpenseReport(@PathVariable int userId) {
        List<TransactionDto> reports = reportService.generateIncomeExpenseReport(userId);
        if (reports != null && !reports.isEmpty()) {
            return new ResponseEntity<>(reports, HttpStatus.OK);
        } else { 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/budgetreport/{userId}/{startDate}/{endDate}")
    public ResponseEntity<BudgetDto> getBudgetReport(@PathVariable int userId, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
    	BudgetDto report = reportService.getBudgetReport(userId, startDate, endDate);
        if (report != null) {
            return new ResponseEntity<>(report, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/portfolioreport/{userId}")
    public ResponseEntity<List<Portfolio>> getPortfolioReport(@PathVariable int userId) {
    	List<Portfolio> report = portfolioService.viewAllPortfolios(userId);
        if (report != null) {
            return new ResponseEntity<>(report, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}