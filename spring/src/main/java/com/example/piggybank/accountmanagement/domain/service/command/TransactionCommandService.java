package com.example.piggybank.accountmanagement.domain.service.command;

import com.example.piggybank.accountmanagement.api.dto.request.CategoryIdUpdateRequest;
import com.example.piggybank.accountmanagement.api.dto.response.FromCodefResponse;
import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import java.util.UUID;

public interface TransactionCommandService {
    
    void save(Transaction transaction);

    void updateCategory(CategoryIdUpdateRequest request);

}
