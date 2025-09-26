package com.example.piggybank.accountmanagement.domain.service.query;

import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionQueryService {
    Transaction getTransaction(UUID id);
    List<Transaction> getTransactions(UUID accountId, LocalDateTime startDate, LocalDateTime endDate);
    boolean existsByAccountIdAndTransactionDate(UUID accountId, LocalDateTime localDateTime);
    Transaction getRecentTransaction(UUID accountId);
    long getRecentConsumption(UUID accountId, LocalDateTime from, LocalDateTime to);
}
