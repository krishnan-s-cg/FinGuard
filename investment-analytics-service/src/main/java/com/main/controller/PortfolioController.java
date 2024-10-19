package com.main.controller;

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
        try {
            Portfolio portfolio = portfolioService.addPortfolio(request);
            return new ResponseEntity<>(portfolio, HttpStatus.CREATED);
        } catch (CustomException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{portfolioId}") 
    public ResponseEntity<Portfolio> viewPortfolio(@Valid @PathVariable int portfolioId) {
        try {
            Portfolio portfolio = portfolioService.viewPortfolio(portfolioId);
            return new ResponseEntity<>(portfolio, HttpStatus.OK);
        } catch (CustomException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        
    @PutMapping("/{portfolioId}")
    public ResponseEntity<Portfolio> updatePortfolio(@Valid @PathVariable int portfolioId, @RequestBody PortfolioRequest portfolioRequest) {
        try {
            Portfolio portfolio = portfolioService.updatePortfolio(portfolioId, portfolioRequest);
            return new ResponseEntity<>(portfolio, HttpStatus.OK);
        } catch (CustomException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{portfolioId}")
    public ResponseEntity<Void> deletePortfolio(@Valid @PathVariable int portfolioId) {
        try {
            portfolioService.deletePortfolio(portfolioId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}