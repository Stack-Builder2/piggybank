package com.example.piggybank.domain.auth.entity;

import com.example.piggybank.global.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user_tb")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String ph;

    @Version
    private Long version;

    @Builder
    public User(String email, String password, String ph) {
        this.email = email;
        this.password = password;
        this.ph = ph;
    }

    public void updatePassword(String email, String newPassword) {
        password = newPassword;
    }

    public void delete(String email) {
        status = 99L;
    }
}
