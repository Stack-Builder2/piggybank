package com.example.piggybank.accountmanagement.domain.service.query;

import com.example.piggybank.accountmanagement.domain.entity.Account;
import com.example.piggybank.accountmanagement.infrastructure.repository.AccountRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountQueryServiceImpl implements AccountQueryService {
    
    private final AccountRepository accountRepository;
    
    @Override
    public Account getAccount(UUID userId, String accountNum) {
        Account account = accountRepository.findByUserIdAndAccountNum(userId, accountNum);
        return account;
    }
}
