package com.example.piggybank.domain.account.service;

import com.example.piggybank.domain.account.dto.req.AccountCreateRequest;
import com.example.piggybank.domain.account.dto.req.AccountUpdateRequest;
import com.example.piggybank.domain.account.dto.resp.AccountResponse;
import com.example.piggybank.domain.account.entity.Account;
import com.example.piggybank.domain.account.repository.DomainAccountRepository;
import com.example.piggybank.domain.auth.entity.User;
import com.example.piggybank.domain.auth.repository.UserRepository;
import com.example.piggybank.global.error.ErrorCode;
import com.example.piggybank.global.error.exception.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final DomainAccountRepository accountRepository;

    @Override
    public AccountResponse createAccount(String userId, AccountCreateRequest request) {

        User user = userRepository.findById(UUID.fromString(userId))
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAccount(String userId, UUID accountId, AccountUpdateRequest request) {

        User user = userRepository.findById(UUID.fromString(userId))
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        Account account = accountRepository.findByAccountIdAndUserId(accountId, user.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("존재하지 않은 계좌입니다."));

        account.updateAccount(request.accountNum(), request.bankName());

        accountRepository.save(account);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAccount(String userId, UUID accountId) {

        User user = userRepository.findById(UUID.fromString(userId))
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new EntityNotFoundException("존재하지 않은 계좌입니다."));

        account.deleteAccount();
    }
}
