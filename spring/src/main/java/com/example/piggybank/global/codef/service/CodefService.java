package com.example.piggybank.global.codef.service;

import com.example.piggybank.global.codef.dto.CodefConnectedIdReqDto;
import com.example.piggybank.global.codef.dto.CodefTransactionReqDto;
import org.springframework.http.ResponseEntity;

public interface CodefService {
    String publishCodefAccessToken();
    String publishCodefConnectedId(CodefConnectedIdReqDto codefConnectedIdReqDto);
    String getCodefTransactions(CodefTransactionReqDto codefTransactionReqDto);
}
