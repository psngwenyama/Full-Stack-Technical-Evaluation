package com.enviro.assessment.enviro.assessment.sthembiso.dto;

import com.enviro.assessment.enviro.assessment.sthembiso.enums.ProductType;

import java.math.BigDecimal;

public class ProductDto {

    private Long id;
    private String productName;
    private ProductType productType;
    private BigDecimal balance;

    public ProductDto(){

    }

    public ProductDto(Long id, String productName, ProductType productType, BigDecimal balance) {
        this.id = id;
        this.productName = productName;
        this.productType = productType;
        this.balance = balance;
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
}
