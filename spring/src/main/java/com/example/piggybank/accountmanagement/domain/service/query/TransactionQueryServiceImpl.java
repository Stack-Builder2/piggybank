package com.example.piggybank.accountmanagement.domain.service.query;

import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import com.example.piggybank.accountmanagement.infrastructure.repository.TransactionRepository;
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
}
