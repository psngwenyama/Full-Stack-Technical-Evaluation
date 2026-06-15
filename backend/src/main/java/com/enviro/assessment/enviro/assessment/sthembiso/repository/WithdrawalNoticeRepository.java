package com.enviro.assessment.enviro.assessment.sthembiso.repository;

import com.enviro.assessment.enviro.assessment.sthembiso.entity.WithdrawalNotice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithdrawalNoticeRepository extends JpaRepository<WithdrawalNotice, Long> {

    List<WithdrawalNotice> findByInvestorId(Long investorId);
}
