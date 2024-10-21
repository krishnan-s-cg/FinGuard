package com.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.main.controller.PortfolioController;
import com.main.dto.PortfolioRequest;
import com.main.entity.Portfolio;
import com.main.exception.CustomException;
import com.main.service.PortfolioServiceImpl;

@SpringBootTest
class InvestmentAnalyticsServiceApplicationTests {

    @InjectMocks
    private PortfolioController portfolioController;

    @Mock
    private PortfolioServiceImpl portfolioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Positive Flow Tests
    @Test
    void contextLoads() {
        // Test context loading
    }

    @Test
    void testAddPortfolio() {
        PortfolioRequest request = new PortfolioRequest();
        request.setUserId(1);
        request.setAssetType("mutual funds");
        request.setQuantity(10);
     // Use BigDecimal for prices
        request.setPurchasePrice(BigDecimal.valueOf(100.0));
        request.setCurrentPrice(BigDecimal.valueOf(120.0));
        
        // Set a valid purchase date or ensure your service handles null
        request.setPurchaseDate(LocalDate.parse("2023-01-01"));

        Portfolio portfolio = new Portfolio();
        portfolio.setUserId(1);
        portfolio.setAssetType("mutual funds");

        when(portfolioService.addPortfolio(any())).thenReturn(portfolio);

        ResponseEntity<Portfolio> response = portfolioController.addPortfolio(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(portfolio, response.getBody());
        verify(portfolioService, times(1)).addPortfolio(any());
    }

    @Test
    void testViewPortfolio() {
        int portfolioId = 1;
        Portfolio portfolio = new Portfolio();
        portfolio.setUserId(1);
        portfolio.setAssetType("mutual funds");

        when(portfolioService.viewPortfolio(portfolioId)).thenReturn(portfolio);

        ResponseEntity<Portfolio> response = portfolioController.viewPortfolio(portfolioId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(portfolio, response.getBody());
        verify(portfolioService, times(1)).viewPortfolio(portfolioId);
    }

    // Negative Flow Tests
    @Test
    void testAddPortfolio_InvalidRequest() {
        PortfolioRequest request = new PortfolioRequest(); // Invalid (missing fields)

        assertThrows(CustomException.class, () -> portfolioController.addPortfolio(request));
    }

    @Test
    void testViewPortfolio_NotFound() {
        int portfolioId = 1;

        when(portfolioService.viewPortfolio(portfolioId)).thenThrow(new CustomException("Portfolio not found"));

        ResponseEntity<Portfolio> response = portfolioController.viewPortfolio(portfolioId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        // Optionally check if response body contains the expected message or is empty
    }
}
