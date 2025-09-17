package com.example.piggybank.accountmanagement.domain.service.command;

import com.example.piggybank.accountmanagement.api.dto.response.FromCodefResponse;
import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import com.example.piggybank.accountmanagement.infrastructure.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionCommandServiceImpl implements TransactionCommandService {
    private final TransactionRepository transactionRepository;
    
    
    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
