package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.main.dto.PortfolioRequest;
import com.main.entity.Portfolio;
import com.main.exception.CustomException;
import com.main.service.PortfolioServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/Portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioServiceImpl portfolioService;

    @PostMapping 
    public ResponseEntity<Portfolio> addPortfolio(@Valid @RequestBody PortfolioRequest request) {
      
            Portfolio portfolio = portfolioService.addPortfolio(request);
            return new ResponseEntity<>(portfolio, HttpStatus.CREATED);
        }
    
    @GetMapping("/{portfolioId}") 
    public ResponseEntity<Portfolio> viewPortfolio(@Valid @PathVariable int portfolioId) {
      
            Portfolio portfolio = portfolioService.viewPortfolio(portfolioId);
            return new ResponseEntity<>(portfolio, HttpStatus.OK);
    }
        
    @PutMapping("/{portfolioId}")
    public ResponseEntity<Portfolio> updatePortfolio(@Valid @PathVariable int portfolioId, @RequestBody PortfolioRequest portfolioRequest) {
       
            Portfolio portfolio = portfolioService.updatePortfolio(portfolioId, portfolioRequest);
            return new ResponseEntity<>(portfolio, HttpStatus.OK);
    }

    @DeleteMapping("/{portfolioId}")
    public ResponseEntity<Void> deletePortfolio(@Valid @PathVariable int portfolioId) {
       
            portfolioService.deletePortfolio(portfolioId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping
    public ResponseEntity<List<Portfolio>> viewAllPortfolios() {
        List<Portfolio> portfolios = portfolioService.viewAllPortfolios();
        return new ResponseEntity<>(portfolios, HttpStatus.OK);
    }
}