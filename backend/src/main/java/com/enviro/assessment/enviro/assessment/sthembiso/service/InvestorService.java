package com.enviro.assessment.enviro.assessment.sthembiso.service;

import com.enviro.assessment.enviro.assessment.sthembiso.dto.InvestorDto;
import com.enviro.assessment.enviro.assessment.sthembiso.dto.InvestorPortfolioDto;
import com.enviro.assessment.enviro.assessment.sthembiso.dto.PortfolioDto;
import com.enviro.assessment.enviro.assessment.sthembiso.dto.ProductDto;
import com.enviro.assessment.enviro.assessment.sthembiso.entity.Investor;
import com.enviro.assessment.enviro.assessment.sthembiso.entity.Portfolio;
import com.enviro.assessment.enviro.assessment.sthembiso.exception.ResourceNotFoundException;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.InvestorRepository;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestorService {

    private final InvestorRepository investorRepository;
    private final PortfolioRepository portfolioRepository;

    public InvestorService(InvestorRepository investorRepository,
                           PortfolioRepository portfolioRepository
    )
    {
        this.investorRepository = investorRepository;
        this.portfolioRepository = portfolioRepository;
    }

    public List<InvestorDto> getAllInvestors(){
        return investorRepository.findAll()
                .stream()
                .map(investor -> new InvestorDto(
                        investor.getId(),
                        investor.getFirstName(),
                        investor.getLastName(),
                        investor.getEmail()
                ))
                .collect(Collectors.toList());
    }

    public InvestorPortfolioDto getInvestorPortfolio(Long investorId) {

        Investor investor = investorRepository.findById(investorId)
                .orElseThrow(() -> new ResourceNotFoundException("Investor not found"));

        List<Portfolio> portfolios = portfolioRepository.findByInvestorId(investorId);

        List<PortfolioDto> portfolioDtos = portfolios.stream()
                .map(portfolio -> new PortfolioDto(
                        portfolio.getId(),
                        portfolio.getPortfolioName(),
                        portfolio.getBalance(),
                        portfolio.getProducts().stream()
                                .map(product -> new ProductDto(
                                        product.getPortfolio().getId(),
                                        product.getProductName(),
                                        product.getProductType(),
                                        product.getBalance()
                                ))
                                .toList()
                ))
                .toList();

        return new InvestorPortfolioDto(
                investor.getId(),
                investor.getFirstName() + " " + investor.getLastName(),
                portfolioDtos
        );
    }
}
