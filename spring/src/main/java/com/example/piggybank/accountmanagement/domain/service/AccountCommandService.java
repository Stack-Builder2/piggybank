package com.example.piggybank.accountmanagement.domain.service;

import com.example.piggybank.accountmanagement.api.dto.request.AccountCreateRequest;
import com.example.piggybank.accountmanagement.api.dto.request.AccountUpdateRequest;
import com.example.piggybank.accountmanagement.api.dto.response.AccountResponse;
import com.example.piggybank.accountmanagement.domain.entity.Account;
import com.example.piggybank.accountmanagement.infrastructure.repository.AccountRepository;
import com.example.piggybank.global.error.exception.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountCommandService {

    private final AccountRepository accountRepository;

    public AccountResponse createAccount(String userId, AccountCreateRequest request) {

        Account account = Account.builder()
                .userId(UUID.fromString(userId))
                .accountNum(request.accountNum())
                .bankName(request.bankName())
                .build();

        accountRepository.save(account);

        return new AccountResponse(
                account.getUserId(),
                account.getAccountNum(),
                account.getBankName()
        );
    }

    public void updateAccount(String userId, UUID accountId, AccountUpdateRequest request) {

        Account account = accountRepository.findByAccountIdAndUserId(accountId, UUID.fromString(userId))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않은 계좌입니다."));

        account.updateAccount(request.accountNum(), request.bankName());
    }

    public void deleteAccount(String userId, UUID accountId) {

        Account account = accountRepository.findByAccountIdAndUserId(accountId, UUID.fromString(userId))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않은 계좌입니다."));

        account.deleteAccount();
    }
}