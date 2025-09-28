package com.example.piggybank.accountmanagement.domain.service.command;

import com.example.piggybank.accountmanagement.api.dto.request.CategoryIdUpdateRequest;
import com.example.piggybank.accountmanagement.api.dto.response.FromCodefResponse;
import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import com.example.piggybank.accountmanagement.infrastructure.repository.TransactionRepository;
import com.example.piggybank.global.error.ErrorCode;
import com.example.piggybank.global.error.exception.BusinessException;
import java.math.BigDecimal;
import java.util.UUID;
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
    public void save(FromCodefResponse dto, UUID accountId) {
        Transaction transaction = Transaction.builder()
            .accountId(accountId)
            .transactionDate(dto.transactionDate())
            .amount(BigDecimal.valueOf(dto.amount()))
            .description(dto.description())
            .inputType(dto.inoutType())
            .build();
        transactionRepository.save(transaction);
    }

    @Override
    public void updateCategory(CategoryIdUpdateRequest request) {
        Transaction transaction = transactionRepository.findById(request.transactionId())
            .orElseThrow(() -> new BusinessException(ErrorCode.TRANSACTION_NOT_FOUND));

        transaction.updateCategory(request.categoryId());
    }

}
