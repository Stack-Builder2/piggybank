package com.example.piggybank.global.codef.service;

import com.example.piggybank.global.codef.dto.CodefConnectedIdReqDto;
import com.example.piggybank.global.codef.dto.CodefTransactionReqDto;
import com.example.piggybank.global.codef.dto.CodefTransactionResDto;
import org.springframework.http.ResponseEntity;

public interface CodefService {
    String publishCodefAccessToken();
    String publishCodefConnectedId(CodefConnectedIdReqDto codefConnectedIdReqDto);
    CodefTransactionResDto getCodefTransactions(CodefTransactionReqDto codefTransactionReqDto);
}
