package com.example.piggybank.accountmanagement.domain.entity;

import com.example.piggybank.domain.payment.entity.InputType;
import com.example.piggybank.global.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
public class Transaction extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "transaction_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID transactionId;

    @Column(name = "account_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID accountId;

    @Column(name = "category_id", nullable = false, columnDefinition = "BINARY(16)")
    private Long categoryId;

    @Column(nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name = "inout_type", nullable = false)
//    @Enumerated(EnumType.STRING)
    private boolean inputType;

    @Column(nullable = false)
    private String description;

    @Version
    private Long version;

    @Builder
    public Transaction(UUID accountId, Long categoryId, BigDecimal amount,
        LocalDateTime transactionDate,
        boolean inputType, String description) {
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.inputType = inputType;
        this.description = description;
    }

    public void updateCategory(Long categoryId) {
        this.categoryId = categoryId;
    }
}

