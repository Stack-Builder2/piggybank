package com.example.piggybank.domain.account.service;

import com.example.piggybank.domain.account.dto.req.AccountCreateRequest;
import com.example.piggybank.domain.account.dto.req.AccountUpdateRequest;
import com.example.piggybank.domain.account.dto.resp.AccountResponse;
import java.util.UUID;

public interface AccountService {

    public AccountResponse createAccount(String userId, AccountCreateRequest request);

    public void updateAccount(String userId, UUID accountId, AccountUpdateRequest request);

    public void deleteAccount(String userID, UUID accountId);

}
