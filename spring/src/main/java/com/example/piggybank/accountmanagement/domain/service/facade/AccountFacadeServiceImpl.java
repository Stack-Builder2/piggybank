package com.example.piggybank.accountmanagement.domain.service.facade;

import com.example.piggybank.accountmanagement.api.dto.request.AccountCreateRequest;
import com.example.piggybank.accountmanagement.api.dto.request.AccountUpdateRequest;
import com.example.piggybank.accountmanagement.api.dto.response.AccountCreateResponse;
import com.example.piggybank.accountmanagement.api.dto.response.AccountUpdateResponse;
import com.example.piggybank.accountmanagement.domain.entity.Account;
import com.example.piggybank.accountmanagement.domain.service.command.AccountCommandService;
import com.example.piggybank.accountmanagement.domain.service.query.AccountQueryService;
import com.example.piggybank.accountmanagement.event.AccountCreatedEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountFacadeServiceImpl implements AccountFacadeService{
    
    private final AccountQueryService accountQueryService;
    private final AccountCommandService accountCommandService;
    private final ApplicationEventPublisher eventPublisher;
    
    @Override
    public AccountCreateResponse createAccount(String userId, AccountCreateRequest request) {
        AccountCreateResponse response = accountCommandService.createAccount(userId, request);
        eventPublisher.publishEvent(new AccountCreatedEvent(this, response.account(), request));
        return response;
    
    }
    
    @Override
    public AccountCreateResponse setConnectedId(String accountId, String userId, String connectedId) {
        
        return accountCommandService.setConnectedId(accountId, userId, connectedId);
    }
    
    @Override
    public AccountUpdateResponse updateAccount(String userId, UUID accountId, AccountUpdateRequest request) {
        
        Account account = accountQueryService.getAccount(UUID.fromString(userId), request.accountNum());
        if (account == null) {
            return new AccountUpdateResponse(
                userId,
                accountId.toString(),
                "계좌 업데이트에 실패했습니다."
            );
        }
        accountCommandService.updateAccount(userId, accountId, request);
        
        return new AccountUpdateResponse(
            userId,
            accountId.toString(),
            "계좌 정보가 변경되었습니다."
        );
    }
    
    @Override
    public void deleteAccount(String userID, UUID accountId) {
        accountCommandService.deleteAccount(userID, accountId);
    }
    
    @Override
    public List<Account> getAccounts(String userId) {
        List<Account> accounts = accountQueryService.getAccounts(UUID.fromString(userId));
        return accounts;
    }
    
    @Override
    public void updateBalance(String userId, String accountId, long balance) {
        accountCommandService.updateBalance(userId, UUID.fromString(accountId), balance);
    }
    
    @Override
    public void updateConsumption(String userId, String accountId, long consumption) {
        accountCommandService.updateConsumption(userId, UUID.fromString(accountId), consumption);
    }
    
}
