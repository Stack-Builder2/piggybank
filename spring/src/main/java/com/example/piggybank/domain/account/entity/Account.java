package com.example.piggybank.domain.account.entity;

import com.example.piggybank.global.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "DomainAccount")
@Table(name = "account_tb")
@Getter
@Setter
@NoArgsConstructor
public class Account extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    private UUID accountId;

    @Column(name = "user_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(nullable = false)
    private String accountNum;

    @Column(nullable = false)
    private String bankName;

    @Version
    private Long version;

    @Builder
    public Account(UUID userId, String accountNum, String bankName) {
        this.userId = userId;
        this.accountNum = accountNum;
        this.bankName = bankName;
    }

    public void updateAccount(String accountNum, String bankName) {
        if (accountNum != null && !accountNum.isBlank()) {
            this.accountNum = accountNum;
        }
        if (bankName != null && !bankName.isBlank()) {
            this.bankName = bankName;
        }
        status = 10L;
    }

    public void deleteAccount() {
        status = 99L;
    }
}
