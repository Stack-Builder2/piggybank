package com.example.piggybank.global.codef.service;

import com.example.piggybank.global.codef.dto.CodefConnectedIdReqDto;
import com.example.piggybank.global.codef.dto.CodefTransactionReqDto;
import com.example.piggybank.global.codef.dto.CodefTransactionResDto;
import java.util.List;

public interface CodefService {
    String publishCodefAccessToken();
    String publishCodefConnectedId(CodefConnectedIdReqDto codefConnectedIdReqDto);
    List<CodefTransactionResDto.TranHistory> getCodefTransactions(CodefTransactionReqDto codefTransactionReqDto);
}
