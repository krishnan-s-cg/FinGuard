package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Budget;

public interface BudgetRepository extends JpaRepository<Budget,Integer>{

	 List<Budget> findByUserId(int userId);
	
}
