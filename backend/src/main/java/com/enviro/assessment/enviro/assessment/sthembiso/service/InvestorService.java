package com.enviro.assessment.enviro.assessment.sthembiso.service;

import com.enviro.assessment.enviro.assessment.sthembiso.entity.Investor;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.InvestorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorService {

    private InvestorRepository investorRepository;

    public InvestorService(InvestorRepository investorRepository){
        this.investorRepository = investorRepository;
    }

    public List<Investor> getAllInvestors(){
        return investorRepository.findAll();
    }

}
