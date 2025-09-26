package com.example.piggybank.accountmanagement.domain.service.facade;

import com.example.piggybank.accountmanagement.api.dto.request.AccountCreateRequest;
import com.example.piggybank.accountmanagement.api.dto.request.AccountUpdateRequest;
import com.example.piggybank.accountmanagement.api.dto.response.AccountCreateResponse;
import com.example.piggybank.accountmanagement.api.dto.response.AccountUpdateResponse;
import com.example.piggybank.accountmanagement.domain.entity.Account;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountFacadeService {
    
    public AccountCreateResponse createAccount(String userId, AccountCreateRequest request);
    public AccountCreateResponse setConnectedId(String accountId, String userId, String connectedId);
    public AccountUpdateResponse updateAccount(String userId, UUID accountId, AccountUpdateRequest request);
    public void deleteAccount(String userID, UUID accountId);
    public List<Account> getAccounts(String userId);
    public void updateBalance(UUID accountId, String balance);
    public void updateConsumption(UUID accountId);
    public String compareConsumption(UUID userId, BigDecimal limit);
    public String compareBalance(UUID userId,BigDecimal goal);
}
