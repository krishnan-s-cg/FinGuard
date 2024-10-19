package com.main.controller;

import com.main.dto.DebtRequest;
import com.main.entity.Debt;
import com.main.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/debts") 
public class DebtController {

    @Autowired
    private DebtService debtService;

    @PostMapping
    public Debt createDebt(@RequestBody DebtRequest debtRequest) {
        return debtService.createDebt(debtRequest);
    }

    @GetMapping("/{loanId}")
    public Debt getDebtById(@PathVariable Integer loanId) {
    	return debtService.getDebtById(loanId);
    }

//    @PutMapping("/{loanId}")
//    public Debt updateDebt(@PathVariable Integer loanId, double amount) {
//    	return debtService.updateDebt(loanId, amount);
//    }

    @DeleteMapping("/{loanId}")
    public void deleteDebt(@PathVariable Integer loanId) {
        debtService.deleteDebt(loanId);
    }

    @GetMapping("/user/{userId}")
    public List<Debt> getDebtsByUserId(@PathVariable Integer userId) {
        List<Debt> debts = debtService.getDebtsByUserId(userId);
        return debts.stream().map(x->x).collect(Collectors.toList());
    }
}
