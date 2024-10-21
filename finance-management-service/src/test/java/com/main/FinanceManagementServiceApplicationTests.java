//package com.main;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.util.Optional;
//import java.util.List;
//import java.util.Arrays;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.main.dto.BudgetDto;
//import com.main.entity.Budget;
//import com.main.exception.BudgetNotFoundException;
//import com.main.exception.InvalidBudgetException;
//import com.main.exception.RemainingAmountException;
//import com.main.proxy.UserClient;
//import com.main.repository.BudgetRepository;
//import com.main.service.BudgetServiceImpl;
//
//@SpringBootTest
//
//class FinanceManagementServiceApplicationTests {
//
//	    @Mock
//	    private BudgetRepository budgetRepository;
//
//	    @Mock
//	    private UserClient userClient;
//
//	    @InjectMocks
//	    private BudgetServiceImpl budgetService;
//
//	    @BeforeEach
//	    public void setUp() {
//	        MockitoAnnotations.initMocks(this);
//	    }
//
//	    @Test
//	    public void testCreateBudgetService_Success() {
//	        BudgetDto budgetDto = new BudgetDto();
//	        budgetDto.setUserId(1);
//	        budgetDto.setAmount(1000);
//	        budgetDto.setSpentAmount(200);
//	        budgetDto.setCategory("Food");
//
//	        Budget budget = new Budget();
//	        budget.setUserId(1);
//	        budget.setAmount(1000);
//	        budget.setSpentAmount(200);
//	        budget.setCategory("Food");
//
//	        when(budgetRepository.save(any(Budget.class))).thenReturn(budget);
//
//	        Budget createdBudget = budgetService.createBudgetService(budgetDto);
//	        assertNotNull(createdBudget);
//	        assertEquals(1, createdBudget.getUserId());
//	        assertEquals(1000, createdBudget.getAmount());
//	        assertEquals("Food", createdBudget.getCategory());
//
//	        verify(budgetRepository, times(1)).save(any(Budget.class));
//	    }
//
//	    @Test
//	    public void testCreateBudgetService_InvalidBudgetAmount() {
//	        BudgetDto budgetDto = new BudgetDto();
//	        budgetDto.setUserId(1);
//	        budgetDto.setAmount(0); // Invalid amount
//	        budgetDto.setSpentAmount(200);
//
//	        assertThrows(InvalidBudgetException.class, () -> {
//	            budgetService.createBudgetService(budgetDto);
//	        });
//
//	        verify(budgetRepository, never()).save(any(Budget.class));
//	    }
//
//	    @Test
//	    public void testGetBudgetByIdService_Success() {
//	        Budget budget = new Budget();
//	        budget.setUserId(1);
//	        budget.setAmount(1000);
//	        budget.setSpentAmount(200);
//
//	        when(budgetRepository.findById(1)).thenReturn(Optional.of(budget));
//
//	        Budget fetchedBudget = budgetService.getBudgetByIdService(1);
//	        assertNotNull(fetchedBudget);
//	        assertEquals(1, fetchedBudget.getUserId());
//
//	        verify(budgetRepository, times(1)).findById(1);
//	    }
//
//	    @Test
//	    public void testGetBudgetByIdService_BudgetNotFound() {
//	        when(budgetRepository.findById(1)).thenReturn(Optional.empty());
//
//	        assertThrows(BudgetNotFoundException.class, () -> {
//	            budgetService.getBudgetByIdService(1);
//	        });
//
//	        verify(budgetRepository, times(1)).findById(1);
//	    }
//
//	    @Test
//	    public void testUpdateBudgetService_Success() {
//	        Budget budget = new Budget();
//	        budget.setUserId(1);
//	        budget.setAmount(1000);
//
//	        BudgetDto budgetDto = new BudgetDto();
//	        budgetDto.setAmount(2000);
//	        budgetDto.setCategory("Entertainment");
//
//	        when(budgetRepository.findById(1)).thenReturn(Optional.of(budget));
//	        when(budgetRepository.save(any(Budget.class))).thenReturn(budget);
//
//	        Budget updatedBudget = budgetService.updateBudgetService(1, budgetDto);
//	        assertNotNull(updatedBudget);
//	        assertEquals(2000, updatedBudget.getAmount());
//	        assertEquals("Entertainment", updatedBudget.getCategory());
//
//	        verify(budgetRepository, times(1)).findById(1);
//	        verify(budgetRepository, times(1)).save(any(Budget.class));
//	    }
//
//	    @Test
//	    public void testUpdateBudgetService_InvalidBudgetAmount() {
//	        BudgetDto budgetDto = new BudgetDto();
//	        budgetDto.setAmount(-100); // Invalid amount
//
//	        assertThrows(InvalidBudgetException.class, () -> {
//	            budgetService.updateBudgetService(1, budgetDto);
//	        });
//
//	        verify(budgetRepository, never()).save(any(Budget.class));
//	    }
//
//	    @Test
//	    public void testDeleteBudgetService_Success() {
//	        Budget budget = new Budget();
//	        budget.setUserId(1);
//
//	        when(budgetRepository.findById(1)).thenReturn(Optional.of(budget));
//
//	        budgetService.deleteBudgetService(1);
//
//	        verify(budgetRepository, times(1)).delete(budget);
//	    }
//
//	    @Test
//	    public void testGetUserBudgetsService() {
//	        Budget budget1 = new Budget();
//	        budget1.setUserId(1);
//	        Budget budget2 = new Budget();
//	        budget2.setUserId(1);
//
//	        when(budgetRepository.findByUserId(1)).thenReturn(Arrays.asList(budget1, budget2));
//
//	        List<Budget> budgets = budgetService.getUserBudgetsService(1);
//	        assertNotNull(budgets);
//	        assertEquals(2, budgets.size());
//
//	        verify(budgetRepository, times(1)).findByUserId(1);
//	    }
//
//	    @Test
//	    public void testGetRemainingAmountService_Success() {
//	        Budget budget = new Budget();
//	        budget.setUserId(1);
//	        budget.setAmount(1000);
//	        budget.setSpentAmount(500);
//
//	        when(budgetRepository.findById(1)).thenReturn(Optional.of(budget));
//
//	        double remainingAmount = budgetService.getRemainingAmountService(1);
//	        assertEquals(500, remainingAmount);
//
//	        verify(budgetRepository, times(1)).findById(1);
//	    }
//
//	    @Test
//	    public void testGetRemainingAmountService_SpentExceedsBudget() {
//	        Budget budget = new Budget();
//	        budget.setUserId(1);
//	        budget.setAmount(1000);
//	        budget.setSpentAmount(1200); // Spent exceeds budget
//
//	        when(budgetRepository.findById(1)).thenReturn(Optional.of(budget));
//
//	        assertThrows(RemainingAmountException.class, () -> {
//	            budgetService.getRemainingAmountService(1);
//	        });
//
//	        verify(budgetRepository, times(1)).findById(1);
//	    }
//
//}
//
//
//
//
//
//
