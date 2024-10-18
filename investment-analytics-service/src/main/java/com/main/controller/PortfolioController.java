package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.main.dto.PortfolioRequest;
import com.main.entity.Portfolio;
import com.main.service.PortfolioServiceImpl;

public class PortfolioController {

	@Autowired
    private PortfolioServiceImpl portfolioService;

    @PostMapping
    public ResponseEntity<Portfolio> addPortfolio(@RequestBody PortfolioRequest request) {
        Portfolio portfolio = portfolioService.addPortfolio(request);
        return ResponseEntity.ok(portfolio);
    }

    @GetMapping("/{portfolioId}")
    public ResponseEntity<Portfolio> viewPortfolio(@PathVariable int portfolioId) {
        Portfolio portfolio = portfolioService.viewPortfolio(portfolioId);
        return ResponseEntity.ok(portfolio);
    }
}
