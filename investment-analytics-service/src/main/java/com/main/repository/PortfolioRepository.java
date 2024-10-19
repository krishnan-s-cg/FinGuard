package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Portfolio;

public interface PortfolioRepository extends JpaRepository <Portfolio, Integer>{

}
