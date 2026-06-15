package com.enviro.assessment.enviro.assessment.sthembiso.entity;

import com.enviro.assessment.enviro.assessment.sthembiso.enums.ProductType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    public Product(){

    }

    public Product(String productName, ProductType productType, BigDecimal balance, Portfolio portfolio) {
        this.productName = productName;
        this.productType = productType;
        this.balance = balance;
        this.portfolio = portfolio;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public ProductType getProductType() {
        return productType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}
