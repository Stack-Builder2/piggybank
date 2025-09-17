package com.example.piggybank.accountmanagement.api.controller;

import com.example.piggybank.accountmanagement.api.dto.request.TransactionCreateRequest;
import com.example.piggybank.accountmanagement.api.dto.response.TransactionResponse;
import com.example.piggybank.accountmanagement.domain.service.facade.TransactionFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionFacadeService transactionService;
        
        @Operation(summary = "거래 소비 패턴 분석 조회", description = "계좌별 거래 소비 패턴 분석 조회")
        @GetMapping("/{id}")
        public ResponseEntity<TransactionResponse> getTransaction(@PathVariable UUID id) {
            return ResponseEntity.ok(transactionService.getTransaction(id));
        }
        
        @Operation(summary = "거래내역 저장 및 조회", description = "계좌 거래내역 저장 후 조회")
        @GetMapping("/transaction")
        public ResponseEntity<String> createTransactions(@AuthenticationPrincipal String userId, TransactionCreateRequest request) {
            transactionService.getTransactions(UUID.fromString(userId), request);
            return ResponseEntity.ok(null);
        }
    
    }
    

