package com.example.piggybank.accountmanagement.domain.service.query;

import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import java.time.LocalDateTime;
import java.util.UUID;

public interface TransactionQueryService {
    Transaction getTransaction(UUID id);
    Transaction findTransactionByAccountIdAndDateTime(UUID accountId, LocalDateTime dateTime);
    
    boolean existsByAccountIdAndTransactionDate(UUID accountId, LocalDateTime localDateTime);
}
