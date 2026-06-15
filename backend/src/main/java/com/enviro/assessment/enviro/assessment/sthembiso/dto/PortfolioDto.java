package com.enviro.assessment.enviro.assessment.sthembiso.dto;

import java.math.BigDecimal;
import java.util.List;

public class PortfolioDto {

    private Long id;
    private String portfolioName;
    private BigDecimal balance;
    private List<ProductDto> products;

    public PortfolioDto(){

    }

    public PortfolioDto(Long id, String portfolioName, BigDecimal balance, List<ProductDto> products) {
        this.id = id;
        this.portfolioName = portfolioName;
        this.balance = balance;
        this.products = products;
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

    public List<ProductDto> getProducts() {
        return products;
    }
}
