package com.example.piggybank.accountmanagement.domain.service.query;

import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import com.example.piggybank.accountmanagement.infrastructure.repository.TransactionRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionQueryServiceImpl implements TransactionQueryService {
    
    private final TransactionRepository transactionRepository;
    
    @Override
    public Transaction getTransaction(UUID id) {
        Transaction transaction = transactionRepository.findById(id).
            orElse(null);
        return transaction;
    }
    
    @Override
    public Transaction findTransactionByAccountIdAndDateTime(UUID accountId, LocalDateTime dateTime) {
        Transaction transaction = transactionRepository.findByAccountIdAndTransactionDate(accountId, dateTime);
        return transaction;
    }
    
    @Override
    public boolean existsByAccountIdAndTransactionDate(UUID accountId,
        LocalDateTime localDateTime) {
        
        return transactionRepository.existsByAccountIdAndTransactionDate(accountId, localDateTime);
    }
}
