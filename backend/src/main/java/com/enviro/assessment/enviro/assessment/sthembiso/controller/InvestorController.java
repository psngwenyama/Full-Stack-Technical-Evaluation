package com.enviro.assessment.enviro.assessment.sthembiso.controller;

import com.enviro.assessment.enviro.assessment.sthembiso.entity.Investor;
import com.enviro.assessment.enviro.assessment.sthembiso.service.InvestorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/investors")
public class InvestorController {

    private final InvestorService investorService;

    public InvestorController(InvestorService investorService){
        this.investorService = investorService;
    }

    @GetMapping
    public List<Investor> getAllInvestors(){
        return investorService.getAllInvestors();
    }

    @GetMapping("/{id}")
    public Investor getInvestorById(@PathVariable Long id){
        return investorService.getInvestorById(id);
    }

}
