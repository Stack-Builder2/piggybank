package com.example.piggybank.accountmanagement.domain.service.facade;

import static com.example.piggybank.global.error.ErrorCode.BALANCE_NOT_FOUND;

import com.example.piggybank.accountmanagement.api.dto.request.AccountCreateRequest;
import com.example.piggybank.accountmanagement.api.dto.request.AccountUpdateRequest;
import com.example.piggybank.accountmanagement.api.dto.response.AccountCreateResponse;
import com.example.piggybank.accountmanagement.api.dto.response.AccountUpdateResponse;
import com.example.piggybank.accountmanagement.domain.entity.Account;
import com.example.piggybank.accountmanagement.domain.service.command.AccountCommandService;
import com.example.piggybank.accountmanagement.domain.service.query.AccountQueryService;
import com.example.piggybank.accountmanagement.domain.service.query.TransactionQueryService;
import com.example.piggybank.accountmanagement.event.AccountCreatedEvent;
import com.example.piggybank.global.error.ErrorCode;
import com.example.piggybank.global.error.exception.BusinessException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private final TransactionQueryService transactionQueryService;
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
    public void updateBalance(UUID accountId, String balance) {
        if (balance == null || balance.trim().isEmpty()) {
            throw new BusinessException("Balance cannot be null or empty", BALANCE_NOT_FOUND);
        }

        accountCommandService.updateBalance(accountId, Long.valueOf(balance));
    }
    
    @Override
    public void updateConsumption(UUID accountId) {
        
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime from = now.minusMonths(1);
        
        long consumption = transactionQueryService.getRecentConsumption(accountId, from, now);
        
        accountCommandService.updateConsumption(accountId, consumption);
    }
    
    @Override
    public String compareConsumption(UUID userId, BigDecimal limit) {
        BigDecimal consumption = BigDecimal.valueOf(accountQueryService.getUserConsumption(userId));
        if(consumption.compareTo(limit) > 0){
            return "한도금액을 초과했습니다.";
        }
        return "한도 금액을 초과하지 않았습니다.";
    }
    
    @Override
    public String compareBalance(UUID userId, BigDecimal goal) {
        BigDecimal balance = BigDecimal.valueOf(accountQueryService.getUserBalance(userId));
        if(balance.compareTo(goal) > 0){
            return "목표 금액을 초과했습니다.";
        }
        return "목표 금액을 초과하지 못했습니다.";
    }
    
}
