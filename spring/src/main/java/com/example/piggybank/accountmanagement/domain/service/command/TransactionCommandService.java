package com.example.piggybank.accountmanagement.domain.service.command;

import com.example.piggybank.accountmanagement.api.dto.response.FromCodefResponse;
import com.example.piggybank.accountmanagement.domain.entity.Transaction;

public interface TransactionCommandService {
    
    void save(Transaction transaction);
}
