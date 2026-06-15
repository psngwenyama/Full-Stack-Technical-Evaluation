package com.enviro.assessment.enviro.assessment.sthembiso.controller;

import com.enviro.assessment.enviro.assessment.sthembiso.dto.WithdrawalRequestDto;
import com.enviro.assessment.enviro.assessment.sthembiso.dto.WithdrawalResponseDto;
import com.enviro.assessment.enviro.assessment.sthembiso.service.WithdrawalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/withdrawals")
public class WithdrawalController {

    private final WithdrawalService withdrawalService;

    public WithdrawalController(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    @PostMapping
    public WithdrawalResponseDto createWithdrawal(
            @Valid @RequestBody WithdrawalRequestDto request
    ) {
        return withdrawalService.createWithdrawal(request);
    }

    @GetMapping
    public List<WithdrawalResponseDto> getAllWithdrawals() {
        return withdrawalService.getAllWithdrawals();
    }

    @GetMapping("/investor/{investorId}")
    public List<WithdrawalResponseDto> getWithdrawalsByInvestor(@PathVariable Long investorId) {
        return withdrawalService.getWithdrawalsByInvestor(investorId);
    }

    @GetMapping("/export")
    public ResponseEntity<String> exportWithdrawalsCsv(
            @RequestParam(required = false) Long investorId,
            @RequestParam(required = false) String status
    ) {
        String csv = withdrawalService.exportWithdrawalsCsv(investorId, status);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=withdrawals.csv")
                .contentType(MediaType.parseMediaType("text/csv; charset=UTF-8"))
                .body(csv);
    }
}
