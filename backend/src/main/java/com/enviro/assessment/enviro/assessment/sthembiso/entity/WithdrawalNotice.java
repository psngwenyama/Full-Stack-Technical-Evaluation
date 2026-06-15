package com.enviro.assessment.enviro.assessment.sthembiso.entity;

import com.enviro.assessment.enviro.assessment.sthembiso.enums.WithdrawalStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "withdrawal_notices")
public class WithdrawalNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private String reason;

    @Enumerated(EnumType.STRING)
    private WithdrawalStatus status;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public WithdrawalNotice() {
    }

    public WithdrawalNotice(BigDecimal amount, String reason, WithdrawalStatus status,
                            LocalDateTime createdAt, Investor investor,
                            Portfolio portfolio, Product product) {
        this.amount = amount;
        this.reason = reason;
        this.status = status;
        this.createdAt = createdAt;
        this.investor = investor;
        this.portfolio = portfolio;
        this.product = product;
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

    public Investor getInvestor() {
        return investor;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public Product getProduct() {
        return product;
    }

    public void setStatus(WithdrawalStatus withdrawalStatus) {
    }
}
