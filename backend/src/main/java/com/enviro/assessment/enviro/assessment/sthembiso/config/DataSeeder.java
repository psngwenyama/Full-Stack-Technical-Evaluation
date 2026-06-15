package com.enviro.assessment.enviro.assessment.sthembiso.config;

import com.enviro.assessment.enviro.assessment.sthembiso.entity.Investor;
import com.enviro.assessment.enviro.assessment.sthembiso.entity.Portfolio;
import com.enviro.assessment.enviro.assessment.sthembiso.entity.Product;
import com.enviro.assessment.enviro.assessment.sthembiso.enums.ProductType;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.InvestorRepository;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.PortfolioRepository;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(InvestorRepository investorRepository,
                               PortfolioRepository portfolioRepository,
                               ProductRepository productRepository
    )
    {
        return args -> {
            Investor investor1 = new Investor(
                    "Sthembiso",
                    "Ngweyama",
                    "sthembiso.ngwenyama@gmail.com",
                    LocalDate.of(1992,8,12)
            );
            Investor investor2 = new Investor(
                    "Nontobeko",
                    "Zwane",
                    "nontobeko@gmail.com",
                    LocalDate.of(1952,3,8)
            );

            investorRepository.save(investor1);
            investorRepository.save(investor2);

            Portfolio portfolio1 = new Portfolio(
                    "Retirement Fund",
                    new BigDecimal("250000.00"),
                    investor1
            );

            Portfolio portfolio2 = new Portfolio(
                    "Retirement Fund",
                    new BigDecimal("500000.00"),
                    investor2
            );

            Portfolio portfolio3 = new Portfolio(
                    "Savings Account",
                    new BigDecimal("50000.00"),
                    investor1
            );

            Portfolio portfolio4 = new Portfolio(
                    "Investment Fund",
                    new BigDecimal("75000.00"),
                    investor2
            );

            portfolioRepository.save(portfolio1);
            portfolioRepository.save(portfolio2);
            portfolioRepository.save(portfolio3);
            portfolioRepository.save(portfolio4);

            Product product1 = new Product(
                    "Retirement Product",
                    ProductType.RETIREMENT,
                    new BigDecimal("250000.00"),
                    portfolio1
            );

            Product product2 = new Product(
                    "Retirement Product",
                    ProductType.RETIREMENT,
                    new BigDecimal("500000.00"),
                    portfolio2
            );

            Product product3 = new Product(
                    "Savings Product",
                    ProductType.SAVINGS,
                    new BigDecimal("50000.00"),
                    portfolio1
            );

            Product product4 = new Product(
                    "Investment Product",
                    ProductType.INVESTMENT,
                    new BigDecimal("75000.00"),
                    portfolio2
            );

            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);

        };
    }
}
