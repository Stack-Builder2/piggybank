package com.example.piggybank.accountmanagement.infrastructure.repository;

import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    
    List<Transaction> findByAccountIdAndCreatedAtBetween(
        UUID accountId,
        LocalDateTime startDate,
        LocalDateTime endDate
    );
    
    boolean existsByAccountIdAndTransactionDate(UUID accountId, LocalDateTime transactionDate);
    
    List<Transaction> findByAccountIdAndTransactionDateBetween(UUID accountId, LocalDateTime transactionDateAfter, LocalDateTime transactionDateBefore);
}
