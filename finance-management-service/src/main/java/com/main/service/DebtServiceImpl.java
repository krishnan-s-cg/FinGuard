package com.main.service;

import com.main.dto.DebtRequest;
import com.main.dto.User;
import com.main.entity.Debt;
import com.main.proxy.UserClient;
import com.main.repository.DebtRepository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DebtServiceImpl implements DebtService {

    @Autowired
    private DebtRepository debtRepository;
    
    @Autowired 
    private UserClient userClient;

    public Debt createDebt(DebtRequest debtRequest) {
    	User user = userClient.getUserById(debtRequest.getUserId());
        Debt debt = new Debt();
        debt.setUser(user);
        debt.setLoanType(debtRequest.getLoanType());
        debt.setPrincipalAmount(debtRequest.getPrincipalAmount());
        debt.setInterestRate(debtRequest.getInterestRate());
        debt.setEmiAmount(debtRequest.getEmiAmount());
        debt.setStartDate(debtRequest.getStartDate());
        debt.setEndDate(debtRequest.getEndDate());
        debt.setCreatedAt(new Date(System.currentTimeMillis()));
        debt.setUpdatedAt(new Date(System.currentTimeMillis()));
        return debtRepository.save(debt);
    }

    public Debt getDebtById(Integer loanId) {
        return debtRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Debt not found"));
    }

    public Debt updateDebt(Integer loanId, DebtRequest debtRequest) {
        Debt debt = debtRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Debt not found"));
        // Update properties from debtRequest to debt
        return debtRepository.save(debt);
    }

    public void deleteDebt(Integer loanId) {
        debtRepository.deleteById(loanId);
    }

    public List<Debt> getDebtsByUserId(Integer userId) {
        return debtRepository.findByUserId(userId);
    }
}