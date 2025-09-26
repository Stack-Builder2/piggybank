package com.example.piggybank.accountmanagement.infrastructure.repository;

import com.example.piggybank.accountmanagement.domain.entity.Account;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    <T> List<T> findByUserId(UUID userId, Class<T> type);
    Account findByAccountId(UUID accountId);
    Optional<Account> findByAccountIdAndUserId(UUID accountId, UUID userId);
    
    Account findByUserIdAndAccountNum(UUID userId, String accountNum);
    
    List<Account> findByUserIdAndStatusNot(UUID userId, Long status);
    
    interface ConsumptionOnly{
        Long getConsumption();
    }
    
    interface BalanceOnly{
        Long getBalance();
    }
}