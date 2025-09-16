package com.example.piggybank.accountmanagement.domain.service.query;

import com.example.piggybank.accountmanagement.domain.entity.Account;
import java.util.List;
import java.util.UUID;

public interface AccountQueryService {
    
    public Account getAccount(UUID userId, String accountNum);
    public List<Account> getAccounts(UUID userId);
}