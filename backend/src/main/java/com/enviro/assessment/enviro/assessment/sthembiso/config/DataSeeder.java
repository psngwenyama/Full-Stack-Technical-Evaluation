package com.enviro.assessment.enviro.assessment.sthembiso.config;

import com.enviro.assessment.enviro.assessment.sthembiso.entity.Investor;
import com.enviro.assessment.enviro.assessment.sthembiso.repository.InvestorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedInvestors(InvestorRepository investorRepository){
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
        };
    }
}
