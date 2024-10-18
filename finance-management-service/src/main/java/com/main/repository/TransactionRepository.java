package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.dto.User;
import com.main.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	List<Transaction> findByUser(User user);
}
