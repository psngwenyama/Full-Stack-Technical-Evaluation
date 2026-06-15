package com.enviro.assessment.enviro.assessment.sthembiso.service;

import com.enviro.assessment.enviro.assessment.sthembiso.dto.WithdrawalRequestDto;
import com.enviro.assessment.enviro.assessment.sthembiso.dto.WithdrawalResponseDto;
import com.enviro.assessment.enviro.assessment.sthembiso.entity.Investor;
import com.enviro.assessment.enviro.assessment.sthembiso.entity.Portfolio;
import com.enviro.assessment.enviro.assessment.sthembiso.entity.Product;
import com.enviro.assessment.enviro.assessment.sthembiso.entity.WithdrawalNotice;
import com.enviro.assessment.enviro.assessment.sthembiso.enums.ProductType;
import com.enviro.assessment.enviro.assessment.sthembiso.enums.WithdrawalStatus;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.InvestorRepository;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.PortfolioRepository;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.ProductRepository;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.WithdrawalNoticeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class WithdrawalService {

    private final InvestorRepository investorRepository;
    private final PortfolioRepository portfolioRepository;
    private final ProductRepository productRepository;
    private final WithdrawalNoticeRepository withdrawalNoticeRepository;

    public WithdrawalService(
            InvestorRepository investorRepository,
            PortfolioRepository portfolioRepository,
            ProductRepository productRepository,
            WithdrawalNoticeRepository withdrawalNoticeRepository
    ) {
        this.investorRepository = investorRepository;
        this.portfolioRepository = portfolioRepository;
        this.productRepository = productRepository;
        this.withdrawalNoticeRepository = withdrawalNoticeRepository;
    }

    public WithdrawalResponseDto createWithdrawal(WithdrawalRequestDto request) {
        Investor investor = investorRepository.findById(request.getInvestorId())
                .orElseThrow(() -> new RuntimeException("Investor not found"));

        Portfolio portfolio = portfolioRepository.findById(request.getPortfolioId())
                .orElseThrow(() -> new RuntimeException("Portfolio not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        validateWithdrawalRules(investor, product, request.getAmount());

        BigDecimal withdrawalAmount = request.getAmount();

        product.setBalance(product.getBalance().subtract(withdrawalAmount));
        productRepository.save(product);

        portfolio.setBalance(portfolio.getBalance().subtract(withdrawalAmount));
        portfolioRepository.save(portfolio);

        WithdrawalNotice withdrawalNotice = new WithdrawalNotice(
                withdrawalAmount,
                request.getReason(),
                WithdrawalStatus.APPROVED,
                LocalDateTime.now(),
                investor,
                portfolio,
                product
        );

        WithdrawalNotice savedWithdrawal = withdrawalNoticeRepository.save(withdrawalNotice);

        return mapToResponse(savedWithdrawal);
    }

    public List<WithdrawalResponseDto> getAllWithdrawals() {
        return withdrawalNoticeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<WithdrawalResponseDto> getWithdrawalsByInvestor(Long investorId) {
        return withdrawalNoticeRepository.findByInvestorId(investorId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public String exportWithdrawalsCsv(Long investorId, String status) {
        List<WithdrawalNotice> withdrawals = withdrawalNoticeRepository.findAll();

        if (investorId != null) {
            withdrawals = withdrawals.stream()
                    .filter(w -> w.getInvestor().getId().equals(investorId))
                    .toList();
        }

        if (status != null && !status.isBlank()) {
            withdrawals = withdrawals.stream()
                    .filter(w -> w.getStatus().name().equalsIgnoreCase(status))
                    .toList();
        }

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("M/d/yyyy, h:mm:ss a");

        StringBuilder csv = new StringBuilder();

        csv.append("sep=,\n");
        csv.append("ID,Investor,Portfolio,Product,Amount,Reason,Status,Created At\n");

        for (WithdrawalNotice w : withdrawals) {
            csv.append(w.getId()).append(",");
            csv.append(escapeCsv(w.getInvestor().getFirstName() + " " + w.getInvestor().getLastName())).append(",");
            csv.append(escapeCsv(w.getPortfolio().getPortfolioName())).append(",");
            csv.append(escapeCsv(w.getProduct().getProductName())).append(",");
            csv.append(w.getAmount()).append(",");
            csv.append(escapeCsv(w.getReason())).append(",");
            csv.append(w.getStatus()).append(",");
            csv.append(escapeCsv(w.getCreatedAt().format(formatter))).append("\n");
        }

        return csv.toString();
    }

    private void validateWithdrawalRules(Investor investor, Product product, BigDecimal amount) {
        if (product.getProductType() == ProductType.RETIREMENT) {
            int age = Period.between(investor.getDateOfBirth(), LocalDate.now()).getYears();

            if (age <= 65) {
                throw new RuntimeException(
                        "Retirement withdrawals are only allowed if investor age is greater than 65"
                );
            }
        }

        if (amount.compareTo(product.getBalance()) > 0) {
            throw new RuntimeException("Withdrawal amount cannot exceed product balance");
        }

        BigDecimal ninetyPercent = product.getBalance().multiply(new BigDecimal("0.90"));

        if (amount.compareTo(ninetyPercent) > 0) {
            throw new RuntimeException("Withdrawal amount cannot exceed 90% of product balance");
        }
    }

    private WithdrawalResponseDto mapToResponse(WithdrawalNotice withdrawal) {
        return new WithdrawalResponseDto(
                withdrawal.getId(),
                withdrawal.getAmount(),
                withdrawal.getReason(),
                withdrawal.getStatus(),
                withdrawal.getCreatedAt(),
                withdrawal.getInvestor().getFirstName() + " " + withdrawal.getInvestor().getLastName(),
                withdrawal.getPortfolio().getPortfolioName(),
                withdrawal.getProduct().getProductName()
        );
    }

    private String escapeCsv(String value) {
        if (value == null) {
            return "";
        }

        String escaped = value.replace("\"", "\"\"");

        if (escaped.contains(",") || escaped.contains("\"") || escaped.contains("\n")) {
            return "\"" + escaped + "\"";
        }

        return escaped;
    }
}
