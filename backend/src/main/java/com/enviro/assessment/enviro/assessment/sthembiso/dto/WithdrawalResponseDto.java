package com.enviro.assessment.enviro.assessment.sthembiso.dto;

import com.enviro.assessment.enviro.assessment.sthembiso.enums.WithdrawalStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WithdrawalResponseDto {

    private Long id;
    private BigDecimal amount;
    private String reason;
    private WithdrawalStatus status;
    private LocalDateTime createdAt;
    private String investorName;
    private String portfolioName;
    private String productName;

    public WithdrawalResponseDto(Long id, String s, String portfolioName, String productName, BigDecimal amount, String reason, WithdrawalStatus status, LocalDateTime createdAt){
    }

    public WithdrawalResponseDto(Long id,
                                 BigDecimal amount,
                                 String reason,
                                 WithdrawalStatus status,
                                 LocalDateTime createdAt,
                                 String investorName,
                                 String portfolioName,
                                 String productName) {
        this.id = id;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
        this.createdAt = createdAt;
        this.investorName = investorName;
        this.portfolioName = portfolioName;
        this.productName = productName;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getReason() {
        return reason;
    }

    public WithdrawalStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getInvestorName() {
        return investorName;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public String getProductName() {
        return productName;
    }
}

