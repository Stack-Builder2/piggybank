package com.example.piggybank.accountmanagement.api.controller;

import com.example.piggybank.accountmanagement.api.dto.response.TransactionResponse;
import com.example.piggybank.accountmanagement.domain.service.facade.TransactionFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/payment")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionFacadeService transactionService;
        
        @Operation(summary = "거래 소비 패턴 분석 조회", description = "계좌별 거래 소비 패턴 분석 조회")
        @GetMapping("/{id}")
        public ResponseEntity<TransactionResponse> getTransaction(@PathVariable UUID id) {
            return ResponseEntity.ok(transactionService.getTransaction(id));
        }
        
    }
    
}
