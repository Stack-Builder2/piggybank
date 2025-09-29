package com.example.piggybank.accountmanagement.domain.service.command;

import com.example.piggybank.accountmanagement.api.dto.request.AccountCreateRequest;
import com.example.piggybank.accountmanagement.api.dto.request.AccountUpdateRequest;
import com.example.piggybank.accountmanagement.api.dto.response.AccountCreateResponse;
import com.example.piggybank.accountmanagement.domain.entity.Account;
import com.example.piggybank.accountmanagement.infrastructure.repository.AccountRepository;
import com.example.piggybank.global.error.ErrorCode;
import com.example.piggybank.global.error.exception.BusinessException;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountCommandServiceImpl implements AccountCommandService {

    private final AccountRepository accountRepository;

    public AccountCreateResponse createAccount(String userId, AccountCreateRequest request) {
        if(accountRepository.findByUserIdAndAccountNum(UUID.fromString(userId), request.accountNum()) != null) return null;

        Account account = Account.builder()
                .userId(UUID.fromString(userId))
                .accountNum(request.accountNum())
                .bankName(request.bankName())
                .build();
        accountRepository.save(account);

        return new AccountCreateResponse(
                account.getUserId(),
                account
        );
    }

    @Override
    public AccountCreateResponse setConnectedId(String accountId, String userId, String connectedId) {
        Account account = accountRepository.findByAccountIdAndUserId(UUID.fromString(accountId), UUID.fromString(userId))
                .orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_FOUND));
        account.setConnectedId(connectedId);

        return new AccountCreateResponse(
                account.getUserId(),
                account
        );
    }

    public void updateAccount(String userId, UUID accountId, AccountUpdateRequest request) {

        Account account = accountRepository.findByAccountIdAndUserId(accountId, UUID.fromString(userId))
                .orElseThrow(() -> new BusinessException(ErrorCode.TRANSACTION_NOT_FOUND));

        account.updateAccount(request.accountNum(), request.bankName());
    }

    public void deleteAccount(String userId, UUID accountId) {

        Account account = accountRepository.findByAccountIdAndUserId(accountId, UUID.fromString(userId))
                .orElseThrow(() -> new BusinessException(ErrorCode.TRANSACTION_NOT_FOUND));

        account.deleteAccount();
    }

    @Override
    public void updateBalance(UUID accountId, long balance) {
        Account account = accountRepository.findByAccountId(accountId);
        account.updateBalance(balance);
    }

    @Override
    public void updateConsumption(UUID accountId, long consumption) {
        Account account = accountRepository.findByAccountId(accountId);
        account.updateConsumption(consumption);
    }
}