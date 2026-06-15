package com.enviro.assessment.enviro.assessment.sthembiso.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "portfolios")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String portfolioName;

    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;

    public Portfolio(){

    }

    public Portfolio(String portfolioName, BigDecimal balance, Investor investor) {
        this.portfolioName = portfolioName;
        this.balance = balance;
        this.investor = investor;
    }

    public Long getId() {
        return id;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }
}
