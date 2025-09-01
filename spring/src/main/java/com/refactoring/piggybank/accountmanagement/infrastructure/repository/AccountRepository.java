package com.refactoring.piggybank.accountmanagement.infrastructure.repository;

import com.refactoring.piggybank.accountmanagement.domain.entity.Account;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByUserId(UUID userId);
    Optional<Account> findByAccountIdAndUserId(UUID accountId, UUID userId);
}