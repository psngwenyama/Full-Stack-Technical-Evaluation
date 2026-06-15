package com.enviro.assessment.enviro.assessment.sthembiso.repository;

import com.enviro.assessment.enviro.assessment.sthembiso.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findByInvestorId(Long investorId);
}
