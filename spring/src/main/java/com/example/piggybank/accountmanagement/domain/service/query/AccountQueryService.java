package com.example.piggybank.accountmanagement.domain.service.query;

import com.example.piggybank.accountmanagement.domain.entity.Account;
import java.util.List;
import java.util.UUID;

public interface AccountQueryService {
    
    public Account getAccount(UUID userId, String accountNum);
    public Account getAccount(UUID userId, UUID accountId);
    public List<Account> getAccounts(UUID userId);
    public long getUserConsumption(UUID userId);
    public long getUserBalance(UUID userId);
}