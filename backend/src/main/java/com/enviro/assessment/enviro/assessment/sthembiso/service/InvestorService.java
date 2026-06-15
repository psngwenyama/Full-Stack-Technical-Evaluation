package com.enviro.assessment.enviro.assessment.sthembiso.service;

import com.enviro.assessment.enviro.assessment.sthembiso.dto.InvestorDto;
import com.enviro.assessment.enviro.assessment.sthembiso.entity.Investor;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.InvestorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestorService {

    private final InvestorRepository investorRepository;

    public InvestorService(InvestorRepository investorRepository){
        this.investorRepository = investorRepository;
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

    public InvestorDto getInvestorById(Long id){
        Investor investor = investorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Investor not found"));
        return new InvestorDto(
                investor.getId(),
                investor.getFirstName(),
                investor.getLastName(),
                investor.getEmail()
        );
    }

}
