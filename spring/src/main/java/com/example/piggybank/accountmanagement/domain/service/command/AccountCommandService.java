package com.example.piggybank.accountmanagement.domain.service.command;

import com.example.piggybank.accountmanagement.api.dto.request.AccountCreateRequest;
import com.example.piggybank.accountmanagement.api.dto.request.AccountUpdateRequest;
import com.example.piggybank.accountmanagement.api.dto.response.AccountCreateResponse;
import java.util.UUID;

public interface AccountCommandService {
    public AccountCreateResponse createAccount(String userId, AccountCreateRequest request);
    AccountCreateResponse setConnectedId(String accountId, String userId, String connectedId);
    public void updateAccount(String userId, UUID accountId, AccountUpdateRequest request);
    public void deleteAccount(String userId, UUID accountId);
}
