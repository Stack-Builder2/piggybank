package com.example.piggybank.accountmanagement.domain.service.facade;

import com.example.piggybank.accountmanagement.api.dto.request.AccountCreateRequest;
import com.example.piggybank.accountmanagement.api.dto.request.AccountUpdateRequest;
import com.example.piggybank.accountmanagement.api.dto.response.AccountCreateResponse;
import com.example.piggybank.accountmanagement.api.dto.response.AccountUpdateResponse;
import java.util.UUID;

public interface AccountFacadeService {
    
    public AccountCreateResponse createAccount(String userId, AccountCreateRequest request);
    public AccountCreateResponse setConnectedId(String accountId, String userId, String connectedId);
    public AccountUpdateResponse updateAccount(String userId, UUID accountId, AccountUpdateRequest request);
    public void deleteAccount(String userID, UUID accountId);
    
}
