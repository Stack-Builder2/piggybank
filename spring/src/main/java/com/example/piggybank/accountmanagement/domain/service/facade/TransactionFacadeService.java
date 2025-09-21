package com.example.piggybank.accountmanagement.domain.service.facade;

import com.example.piggybank.accountmanagement.api.dto.request.CreateTransactionRequest;
import com.example.piggybank.accountmanagement.api.dto.request.GetTransactionRequest;
import com.example.piggybank.accountmanagement.api.dto.response.TransactionResponse;
import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import com.example.piggybank.global.codef.dto.res.CodefTransactionResDto.TranHistory;
import java.util.List;
import java.util.UUID;

public interface TransactionFacadeService {
    public TransactionResponse getTransaction(UUID paymentId);
    public void getTransactionsByCodef(UUID userId, CreateTransactionRequest request);
    public void createTransactions(List<TranHistory> transactions, UUID accountId);
    public List<Transaction> getTransactions(GetTransactionRequest request);
}
