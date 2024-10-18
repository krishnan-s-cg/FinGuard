package com.main.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.PortfolioRequest;
import com.main.entity.Portfolio;
import com.main.repository.PortfolioRepository;

@Service
public class PortfolioServiceImpl implements PortfolioService{
	 @Autowired
	    private PortfolioRepository portfolioRepository;

	    public Portfolio addPortfolio(PortfolioRequest request) {
	        Portfolio portfolio = new Portfolio();
	        portfolio.setUserId(request.getUserId());
	        portfolio.setAssetType(request.getAssetType());
	        portfolio.setQuantity(request.getQuantity());
	        portfolio.setPurchasePrice(request.getPurchasePrice());
	        portfolio.setCurrentPrice(request.getCurrentPrice());
	        portfolio.setPurchaseDate(request.getPurchaseDate());
	        return portfolioRepository.save(portfolio);
	    }

	    public Portfolio viewPortfolio(int portfolioId) {
	        return portfolioRepository.findById(portfolioId)
	                .orElseThrow(() -> new RuntimeException("Portfolio not found"));
	    }
	}