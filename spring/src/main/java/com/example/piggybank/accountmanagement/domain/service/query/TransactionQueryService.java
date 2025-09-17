package com.example.piggybank.accountmanagement.domain.service.query;

import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import java.util.UUID;

public interface TransactionQueryService {
    Transaction getTransaction(UUID id);
}
