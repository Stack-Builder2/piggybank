package com.example.piggybank.accountmanagement.infrastructure.repository;

import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    
    Transaction findByAccountIdAndTransactionDate(UUID accountId, LocalDateTime dateTime);
    
    boolean existsByAccountIdAndTransactionDate(UUID accountId, LocalDateTime transactionDate);
}
