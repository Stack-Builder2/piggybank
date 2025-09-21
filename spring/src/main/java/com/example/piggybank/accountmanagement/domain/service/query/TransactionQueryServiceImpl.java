package com.example.piggybank.accountmanagement.domain.service.query;

import com.example.piggybank.accountmanagement.api.dto.request.CreateTransactionRequest;
import com.example.piggybank.accountmanagement.api.dto.request.GetTransactionRequest;
import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import com.example.piggybank.accountmanagement.infrastructure.repository.TransactionRepository;
import com.example.piggybank.global.error.exception.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionQueryServiceImpl implements TransactionQueryService {
    
    private final TransactionRepository transactionRepository;
    
    @Override
    public Transaction getTransaction(UUID id) {
        return transactionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("거래내역이 존재하지 않습니다."));
    }
    
    @Override
    public List<Transaction> getTransactions(UUID accountId, LocalDateTime startDate, LocalDateTime endDate) {
        
        List<Transaction> transactions = new ArrayList<>();
        transactions = transactionRepository.findByAccountIdAndTransactionDateBetween(accountId, startDate, endDate);
        return transactions;
    }
    
    @Override
    public boolean existsByAccountIdAndTransactionDate(UUID accountId,
        LocalDateTime localDateTime) {
        
        return transactionRepository.existsByAccountIdAndTransactionDate(accountId, localDateTime);
    }
    
    
    
}
