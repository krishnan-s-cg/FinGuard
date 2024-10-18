package com.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.main.dto.DebtRequest;
import com.main.entity.Debt;

public interface DebtService {
	public Debt createDebt(DebtRequest debtRequest);

    public Debt getDebtById(int loanId); 

    public Debt updateDebt(int loanId, DebtRequest debtRequest);

    public void deleteDebt(int loanId);

    public List<Debt> getDebtsByUserId(int userId);

}
