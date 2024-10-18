package com.main.service;

import java.util.List;

import com.main.dto.DebtRequest;
import com.main.entity.Debt;

public interface DebtService {
	public Debt createDebt(DebtRequest debtRequest);

    public Debt getDebtById(Integer loanId); 

    public Debt updateDebt(Integer loanId, DebtRequest debtRequest);

    public void deleteDebt(Integer loanId);

    public List<Debt> getDebtsByUserId(Integer userId);

}
