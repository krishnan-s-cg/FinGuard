package com.main.service;

import com.main.dto.PortfolioRequest;
import com.main.entity.Portfolio;

public interface PortfolioService {
    public Portfolio addPortfolio(PortfolioRequest request);
    public Portfolio viewPortfolio(int portfolioId);
}
