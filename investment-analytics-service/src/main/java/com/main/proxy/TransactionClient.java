package com.main.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.main.dto.PortfolioRequest;
import com.main.dto.TransactionDto;
import com.main.entity.Transaction;

@FeignClient("finance-management-service")
public interface TransactionClient {
	@PostMapping("/transactions/user/portfolio")
	Void portfolioTransaction(PortfolioRequest portfolioRequest);
	
}
