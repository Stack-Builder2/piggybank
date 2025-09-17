package com.example.piggybank.accountmanagement.domain.service.facade;

import com.example.piggybank.accountmanagement.api.dto.request.TransactionCreateRequest;
import com.example.piggybank.accountmanagement.api.dto.request.TransactionRequest;
import com.example.piggybank.accountmanagement.api.dto.response.TransactionResponse;
import com.example.piggybank.global.codef.dto.res.CodefTransactionResDto.TranHistory;
import java.util.List;
import java.util.UUID;

public interface TransactionFacadeService {
    public TransactionResponse getTransaction(UUID paymentId);
    public void getTransactions(UUID userId, TransactionCreateRequest request);
    public List<Object> createTransactions(List<TranHistory> transactions, UUID accountId);
}
