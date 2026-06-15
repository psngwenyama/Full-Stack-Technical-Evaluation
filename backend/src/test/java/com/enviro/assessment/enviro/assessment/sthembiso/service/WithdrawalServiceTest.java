package com.enviro.assessment.enviro.assessment.sthembiso.service;

import com.enviro.assessment.enviro.assessment.sthembiso.dto.WithdrawalRequestDto;
import com.enviro.assessment.enviro.assessment.sthembiso.dto.WithdrawalResponseDto;
import com.enviro.assessment.enviro.assessment.sthembiso.entity.Investor;
import com.enviro.assessment.enviro.assessment.sthembiso.entity.Portfolio;
import com.enviro.assessment.enviro.assessment.sthembiso.entity.Product;
import com.enviro.assessment.enviro.assessment.sthembiso.entity.WithdrawalNotice;
import com.enviro.assessment.enviro.assessment.sthembiso.enums.ProductType;
import com.enviro.assessment.enviro.assessment.sthembiso.enums.WithdrawalStatus;
import com.enviro.assessment.enviro.assessment.sthembiso.exception.BusinessRuleException;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.InvestorRepository;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.PortfolioRepository;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.ProductRepository;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.WithdrawalNoticeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WithdrawalServiceTest {

    @Mock
    private InvestorRepository investorRepository;

    @Mock
    private PortfolioRepository portfolioRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private WithdrawalNoticeRepository withdrawalNoticeRepository;

    @InjectMocks
    private WithdrawalService withdrawalService;

    @Test
    void shouldCreateWithdrawalSuccessfully() {
        Investor investor = new Investor(
                "Sthembiso",
                "Ngweyama",
                "sthembiso.ngwenyama@gmail.com",
                LocalDate.of(1992, 8, 12)
        );

        Portfolio portfolio = new Portfolio(
                "Retirement Fund",
                new BigDecimal("250000.00"),
                investor
        );

        Product product = new Product(
                "Savings Product",
                ProductType.SAVINGS,
                new BigDecimal("50000.00"),
                portfolio
        );

        WithdrawalRequestDto request = new WithdrawalRequestDto();
        request.setInvestorId(1L);
        request.setPortfolioId(1L);
        request.setProductId(3L);
        request.setAmount(new BigDecimal("10000.00"));
        request.setReason("Emergency withdrawal");

        when(investorRepository.findById(1L)).thenReturn(Optional.of(investor));
        when(portfolioRepository.findById(1L)).thenReturn(Optional.of(portfolio));
        when(productRepository.findById(3L)).thenReturn(Optional.of(product));

        when(withdrawalNoticeRepository.save(any(WithdrawalNotice.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        WithdrawalResponseDto response = withdrawalService.createWithdrawal(request);

        assertEquals(0, new BigDecimal("10000.00").compareTo(response.getAmount()));
        assertEquals("Emergency withdrawal", response.getReason());
        assertEquals(WithdrawalStatus.APPROVED, response.getStatus());
        assertEquals("Sthembiso Ngweyama", response.getInvestorName());
        assertEquals("Retirement Fund", response.getPortfolioName());
        assertEquals("Savings Product", response.getProductName());
    }

    @Test
    void shouldRejectRetirementWithdrawalIfInvestorIsYoungerThan65() {
        Investor investor = new Investor(
                "Sthembiso",
                "Ngweyama",
                "sthembiso.ngwenyama@gmail.com",
                LocalDate.of(1992, 8, 12)
        );

        Portfolio portfolio = new Portfolio(
                "Retirement Fund",
                new BigDecimal("250000.00"),
                investor
        );

        Product product = new Product(
                "Retirement Product",
                ProductType.RETIREMENT,
                new BigDecimal("250000.00"),
                portfolio
        );

        WithdrawalRequestDto request = new WithdrawalRequestDto();
        request.setInvestorId(1L);
        request.setPortfolioId(1L);
        request.setProductId(1L);
        request.setAmount(new BigDecimal("10000.00"));
        request.setReason("Retirement withdrawal");

        when(investorRepository.findById(1L)).thenReturn(Optional.of(investor));
        when(portfolioRepository.findById(1L)).thenReturn(Optional.of(portfolio));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        BusinessRuleException exception = assertThrows(
                BusinessRuleException.class,
                () -> withdrawalService.createWithdrawal(request)
        );

        assertEquals(
                "Retirement withdrawals are only allowed if investor age is greater than 65",
                exception.getMessage()
        );
    }

    @Test
    void shouldRejectWithdrawalGreaterThanBalance() {
        Investor investor = new Investor(
                "Sthembiso",
                "Ngweyama",
                "sthembiso.ngwenyama@gmail.com",
                LocalDate.of(1992, 8, 12)
        );

        Portfolio portfolio = new Portfolio(
                "Retirement Fund",
                new BigDecimal("250000.00"),
                investor
        );

        Product product = new Product(
                "Savings Product",
                ProductType.SAVINGS,
                new BigDecimal("50000.00"),
                portfolio
        );

        WithdrawalRequestDto request = new WithdrawalRequestDto();
        request.setInvestorId(1L);
        request.setPortfolioId(1L);
        request.setProductId(3L);
        request.setAmount(new BigDecimal("60000.00"));
        request.setReason("Large withdrawal");

        when(investorRepository.findById(1L)).thenReturn(Optional.of(investor));
        when(portfolioRepository.findById(1L)).thenReturn(Optional.of(portfolio));
        when(productRepository.findById(3L)).thenReturn(Optional.of(product));

        BusinessRuleException exception = assertThrows(
                BusinessRuleException.class,
                () -> withdrawalService.createWithdrawal(request)
        );

        assertEquals(
                "Withdrawal amount cannot exceed product balance",
                exception.getMessage()
        );
    }

    @Test
    void shouldRejectWithdrawalGreaterThanNinetyPercentOfBalance() {
        Investor investor = new Investor(
                "Sthembiso",
                "Ngweyama",
                "sthembiso.ngwenyama@gmail.com",
                LocalDate.of(1992, 8, 12)
        );

        Portfolio portfolio = new Portfolio(
                "Retirement Fund",
                new BigDecimal("250000.00"),
                investor
        );

        Product product = new Product(
                "Savings Product",
                ProductType.SAVINGS,
                new BigDecimal("50000.00"),
                portfolio
        );

        WithdrawalRequestDto request = new WithdrawalRequestDto();
        request.setInvestorId(1L);
        request.setPortfolioId(1L);
        request.setProductId(3L);
        request.setAmount(new BigDecimal("46000.00"));
        request.setReason("Large withdrawal");

        when(investorRepository.findById(1L)).thenReturn(Optional.of(investor));
        when(portfolioRepository.findById(1L)).thenReturn(Optional.of(portfolio));
        when(productRepository.findById(3L)).thenReturn(Optional.of(product));

        BusinessRuleException exception = assertThrows(
                BusinessRuleException.class,
                () -> withdrawalService.createWithdrawal(request)
        );

        assertEquals(
                "Withdrawal amount cannot exceed 90% of product balance",
                exception.getMessage()
        );
    }
}