package com.example.piggybank.accountmanagement.infrastructure.repository;

import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    
    boolean existsByAccountIdAndTransactionDate(UUID accountId, LocalDateTime transactionDate);
    
    List<Transaction> findByAccountIdAndTransactionDateBetween(UUID accountId, LocalDateTime transactionDateAfter, LocalDateTime transactionDateBefore);
    Transaction findTopByAccountIdOrderByTransactionDateDesc(UUID accountId);
    
    @Query("""
        select coalesce(sum(t.amount), 0)
        from Transaction t
        where t.accountId = :accountId
          and t.inputType = true
          and t.transactionDate >= :from
          and t.transactionDate < :to
    """)
    long sumIncomeAmountInPeriod(
        @Param("accountId") UUID accountId,
        @Param("from") LocalDateTime from,
        @Param("to") LocalDateTime to
    );
}
