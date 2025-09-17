package com.example.piggybank.accountmanagement.infrastructure.repository;

import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

}
