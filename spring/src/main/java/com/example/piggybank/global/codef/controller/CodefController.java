package com.example.piggybank.global.codef.controller;

import com.example.piggybank.global.codef.dto.CodefAccessTokenResDto;
import com.example.piggybank.global.codef.dto.CodefConnectedIdReqDto;
import com.example.piggybank.global.codef.dto.CodefTransactionReqDto;
import com.example.piggybank.global.codef.dto.CodefTransactionResDto;
import com.example.piggybank.global.codef.service.CodefService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/codef")
@RequiredArgsConstructor
public class CodefController {

    private final CodefService codefService;
    
    @Operation(summary = "Codef AccessToken 발급", description = "Username : ClientId, password : ClientSecret")
    @PostMapping("/get/token")
    public ResponseEntity<CodefAccessTokenResDto> getAccessToken() {
        String accessToken = codefService.publishCodefAccessToken();
        return ResponseEntity.ok(new CodefAccessTokenResDto(accessToken));
    }
    
    @Operation(summary = "Codef ConnectedId 발급", description = "AccessToken 필요")
    @PostMapping("/get/connectedId")
    public ResponseEntity<String> getConnectedId(CodefConnectedIdReqDto codefConnectedIdReqDto) {
        String connectedId = codefService.publishCodefConnectedId(codefConnectedIdReqDto);
    return ResponseEntity.ok(connectedId);}
    
    
    @Operation(summary = "Codef 계좌 거래내역 조회", description = "AccessToken, ConnectedId 필요")
    @PostMapping("/get/transaction")
    public ResponseEntity<CodefTransactionResDto> getTransaction(CodefTransactionReqDto codefTransactionReqDto) {
        CodefTransactionResDto transaction = codefService.getCodefTransactions(codefTransactionReqDto);
        return ResponseEntity.ok(transaction);
    }
}
