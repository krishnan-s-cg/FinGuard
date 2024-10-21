package com.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.main.dto.BudgetDto;
import com.main.dto.DebtDto;
import com.main.dto.TransactionDto;
import com.main.exception.BudgetNotFoundException;
import com.main.exception.DebtNotFoundException;
import com.main.exception.TransactionNotFoundException;
import com.main.proxy.FinanceClient;
import com.main.service.ReportServiceImpl;

@SpringBootTest
public class ReportAnalyticsServiceApplicationTest {
	@Mock
    private FinanceClient financeClient;

    @InjectMocks
    private ReportServiceImpl reportService;

    
    public void setUp() {
        // Setup can be done here if necessary
    }

    // Positive test case for generateIncomeExpenseReport
    @Test
    public void testGenerateIncomeExpenseReport_success() {
        int userId = 1;
        List<TransactionDto> mockTransactions = Arrays.asList(new TransactionDto(), new TransactionDto());
        when(financeClient.getTransactionsByUserId(userId)).thenReturn(mockTransactions);

        List<TransactionDto> result = reportService.generateIncomeExpenseReport(userId);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    // Negative test case for generateIncomeExpenseReport
    
    @Test
    public void testGenerateIncomeExpenseReport_noTransactions() {
        int userId = 1;
        when(financeClient.getTransactionsByUserId(userId)).thenReturn(Collections.emptyList());

        reportService.generateIncomeExpenseReport(userId);
    }

    // Positive test case for getBudgetReport
    @Test
    public void testGetBudgetReport_success() {
        int userId = 1;
        LocalDate startDate = LocalDate.now().minusMonths(1);
        LocalDate endDate = LocalDate.now();
        BudgetDto mockBudget = new BudgetDto();
        when(financeClient.getBudgetReport(userId, startDate, endDate)).thenReturn(mockBudget);

        BudgetDto result = reportService.getBudgetReport(userId, startDate, endDate);

        assertNotNull(result);
    }

    
    @Test
    public void testGetBudgetReport_noBudget() {
        int userId = 1;
        LocalDate startDate = LocalDate.now().minusMonths(1);
        LocalDate endDate = LocalDate.now();
        when(financeClient.getBudgetReport(userId, startDate, endDate)).thenReturn(null);

        reportService.getBudgetReport(userId, startDate, endDate);
    }

    
    @Test
    public void testGetDebtsReport_success() {
        int userId = 1;
        List<DebtDto> mockDebts = Arrays.asList(new DebtDto(), new DebtDto());
        when(financeClient.getDebtsByUserId(userId)).thenReturn(mockDebts);

        List<DebtDto> result = reportService.getDebtsReport(userId);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetDebtsReport_noDebts() {
        int userId = 1;
        when(financeClient.getDebtsByUserId(userId)).thenReturn(Collections.emptyList());

        reportService.getDebtsReport(userId);
    }
}

