package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.PortfolioRequest;
import com.main.entity.Portfolio;
import com.main.exception.CustomException;
//import com.main.exception.InvalidInputException;
import com.main.repository.PortfolioRepository;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Override
    public Portfolio addPortfolio(PortfolioRequest request) {
        Portfolio portfolio = new Portfolio();
        portfolio.setUserId(request.getUserId()); // Fixed method name
        portfolio.setAssetType(request.getAssetType());
        portfolio.setQuantity(request.getQuantity());
        portfolio.setPurchasePrice(request.getPurchasePrice());
        portfolio.setCurrentPrice(request.getCurrentPrice());
        return portfolioRepository.save(portfolio);
    }

    @Override
    public Portfolio viewPortfolio(int portfolioId) {
        return portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new CustomException("Portfolio not found with ID: " + portfolioId));
    }

    @Override
    public Portfolio updatePortfolio(int portfolioId, PortfolioRequest portfolioRequest) {
        // Check if the portfolio exists
        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new CustomException("Portfolio not found with ID: " + portfolioId));

        // Validate input
        if (portfolioRequest.getAssetType() == null) {
            throw new CustomException("Portfolio Asset Type cannot be null.");
        }

        // Update fields
        portfolio.setAssetType(portfolioRequest.getAssetType());
        // Update other fields as needed
        return portfolioRepository.save(portfolio);
    }

    @Override
    public void deletePortfolio(int portfolioId) {
        // Check if the portfolio exists before attempting to delete
        if (!portfolioRepository.existsById(portfolioId)) {
            throw new CustomException("Portfolio not found with ID: " + portfolioId);
        }

        portfolioRepository.deleteById(portfolioId);
    }
}