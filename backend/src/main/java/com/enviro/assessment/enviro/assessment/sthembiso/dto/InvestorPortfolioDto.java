package com.enviro.assessment.enviro.assessment.sthembiso.dto;

import java.util.List;

public class InvestorPortfolioDto {

    private Long investorId;
    private String investorName;
    private List<PortfolioDto> portfolios;

    public InvestorPortfolioDto(){

    }

    public InvestorPortfolioDto(Long investorId, String investorName, List<PortfolioDto> portfolios) {
        this.investorId = investorId;
        this.investorName = investorName;
        this.portfolios = portfolios;
    }

    public Long getInvestorId() {
        return investorId;
    }

    public String getInvestorName() {
        return investorName;
    }

    public List<PortfolioDto> getPortfolios() {
        return portfolios;
    }
}
