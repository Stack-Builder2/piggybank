package com.example.piggybank.accountmanagement.domain.service.facade;

import java.util.UUID;

public interface CategoryTransactionFacadeService {

    public void updateCategoryByName(UUID transactionId, String categoryName);
    void updateCategory(UUID transactionId, Long categoryId);
    void analyzeAccountTransactions(UUID accountId);
}
