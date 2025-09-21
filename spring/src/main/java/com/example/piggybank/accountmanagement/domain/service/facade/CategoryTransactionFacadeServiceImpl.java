package com.example.piggybank.accountmanagement.domain.service.facade;

import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import com.example.piggybank.accountmanagement.domain.service.query.CategoryQueryService;
import com.example.piggybank.accountmanagement.domain.service.query.TransactionQueryService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryTransactionFacadeServiceImpl implements CategoryTransactionFacadeService {

    private final TransactionQueryService transactionQueryService;
    private final CategoryQueryService categoryQueryService;

    @Override
    public void updateCategoryByName(UUID transactionId, String categoryName) {
        Transaction transaction = transactionQueryService.getTransaction(transactionId);

        Long categoryId = categoryQueryService.getCategoryId(categoryName);

        transaction.updateCategory(categoryId);
    }

    @Override
    @Transactional
    public void updateCategory(UUID transactionId, Long categoryId) {
        Transaction transaction = transactionQueryService.getTransaction(transactionId);
        transaction.updateCategory(categoryId);
    }

    @Override
    public void analyzeAccountTransactions(UUID accountId) {

    }
}
