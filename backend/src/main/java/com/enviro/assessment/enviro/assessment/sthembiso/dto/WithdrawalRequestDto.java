package com.enviro.assessment.enviro.assessment.sthembiso.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class WithdrawalRequestDto {

    @NotNull(message = "Investor ID is required")
    private Long investorId;

    @NotNull(message = "Portfolio ID is required")
    private Long portfolioId;

    @NotNull(message = "Product ID is required")
    private Long productId;

    @NotNull(message = "Withdrawal amount is required")
    @DecimalMin(value = "1.00", message = "Withdrawal amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "Reason is required")
    private String reason;

    public WithdrawalRequestDto() {
    }

    public Long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public Long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

