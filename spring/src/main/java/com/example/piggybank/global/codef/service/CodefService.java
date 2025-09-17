package com.example.piggybank.global.codef.service;

import com.example.piggybank.global.codef.dto.req.CodefConnectedIdReqDto;
import com.example.piggybank.global.codef.dto.req.CodefTransactionReqDto;
import com.example.piggybank.global.codef.dto.res.CodefTransactionResDto;
import java.util.List;

public interface CodefService {
    String publishCodefAccessToken();
    String publishCodefConnectedId(CodefConnectedIdReqDto codefConnectedIdReqDto);
    List<CodefTransactionResDto.TranHistory> getCodefTransactions(CodefTransactionReqDto codefTransactionReqDto);
}
