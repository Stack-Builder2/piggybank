package com.example.piggybank.accountmanagement.domain.service.command;

import com.example.piggybank.accountmanagement.api.dto.request.CategoryIdUpdateRequest;
import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import com.example.piggybank.accountmanagement.infrastructure.repository.TransactionRepository;
import com.example.piggybank.global.error.exception.EntityNotFoundException;
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

    @Override
    public void updateCategory(CategoryIdUpdateRequest request) {
        Transaction transaction = transactionRepository.findById(request.transactionId())
            .orElseThrow(() -> new EntityNotFoundException("존재하지 않은 거래내역입니다."));

        transaction.updateCategory(request.categoryId());
    }

}
