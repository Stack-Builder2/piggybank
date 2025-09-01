package com.refactoring.piggybank.membermanagement.domain.entity;

import com.refactoring.piggybank.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "member")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Embedded
    private Profile profile;

    @Column(name = "role", nullable = false)
    private int role; // 0: ADMIN, 1: USER

    @Version
    private Long version;

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public void softDelete() {
        this.status = 99L;
    }
}