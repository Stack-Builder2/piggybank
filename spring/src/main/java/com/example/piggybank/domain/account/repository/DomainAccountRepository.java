package com.example.piggybank.domain.account.repository;

import com.example.piggybank.domain.account.entity.Account;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainAccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByUserId(UUID userId);
    Optional<Account> findByAccountIdAndUserId(UUID accountId, UUID userId);
}
