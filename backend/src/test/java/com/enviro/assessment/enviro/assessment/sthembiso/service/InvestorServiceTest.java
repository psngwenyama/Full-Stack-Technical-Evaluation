package com.enviro.assessment.enviro.assessment.sthembiso.service;

import com.enviro.assessment.enviro.assessment.sthembiso.dto.InvestorDto;
import com.enviro.assessment.enviro.assessment.sthembiso.entity.Investor;
import com.enviro.assessment.enviro.assessment.sthembiso.exception.ResourceNotFoundException;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.InvestorRepository;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.PortfolioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvestorServiceTest {

    @Mock
    private InvestorRepository investorRepository;

    @Mock
    private PortfolioRepository portfolioRepository;

    @InjectMocks
    private InvestorService investorService;

    @Test
    void shouldReturnAllInvestors() {
        Investor investor1 = new Investor(
                "Sthembiso",
                "Ngweyama",
                "sthembiso.ngwenyama@gmail.com",
                LocalDate.of(1992, 8, 12)
        );

        Investor investor2 = new Investor(
                "Nontobeko",
                "Zwane",
                "nontobeko@gmail.com",
                LocalDate.of(1952, 3, 8)
        );

        when(investorRepository.findAll())
                .thenReturn(List.of(investor1, investor2));

        List<InvestorDto> result = investorService.getAllInvestors();

        assertEquals(2, result.size());

        assertEquals("Sthembiso", result.get(0).getFirstName());
        assertEquals("Ngweyama", result.get(0).getLastName());
        assertEquals("sthembiso.ngwenyama@gmail.com", result.get(0).getEmail());

        assertEquals("Nontobeko", result.get(1).getFirstName());
        assertEquals("Zwane", result.get(1).getLastName());
        assertEquals("nontobeko@gmail.com", result.get(1).getEmail());
    }

    @Test
    void shouldThrowExceptionWhenInvestorDoesNotExist() {
        when(investorRepository.findById(99L))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> investorService.getInvestorPortfolio(99L)
        );

        assertEquals("Investor not found", exception.getMessage());
    }
}