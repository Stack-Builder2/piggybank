package com.example.piggybank.accountmanagement.domain.service.query;

import com.example.piggybank.accountmanagement.domain.entity.Account;
import com.example.piggybank.accountmanagement.infrastructure.repository.AccountRepository;
import com.example.piggybank.global.error.exception.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.security.auth.login.AccountNotFoundException;
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
    
    @Override
    public Account getAccount(UUID userId, UUID accountId) {
        Account account = accountRepository.findByAccountIdAndUserId(accountId,userId)
            .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        return account;
    }
    
    @Override
    public List<Account> getAccounts(UUID userId) {
        List<Account> accounts = new ArrayList<>();
        accountRepository.findByUserIdAndStatusNot(userId, 99L).forEach(account -> accounts.add(account));
        return accounts;
    }
    
    @Override
    public long getUserConsumption(UUID userId) {
        
        List<AccountRepository.ConsumptionOnly> consumptions = accountRepository.findByUserId(userId, AccountRepository.ConsumptionOnly.class);
        
        long consumption = consumptions.stream()
            .map(AccountRepository.ConsumptionOnly::getConsumption)
            .filter(Objects::nonNull)
            .mapToLong(Long::longValue)
            .sum();
        
        return consumption;
    }
    
    @Override
    public long getUserBalance(UUID userId) {
        List<AccountRepository.BalanceOnly> balances = accountRepository.findByUserId(userId, AccountRepository.BalanceOnly.class);
        
        long balance = balances.stream()
            .map(AccountRepository.BalanceOnly::getBalance)
            .filter(Objects::nonNull)
            .mapToLong(Long::longValue)
            .sum();
        
        return balance;
    }
}
