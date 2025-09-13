package com.example.piggybank.membermanagement.domain.entity;

import com.example.piggybank.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "user")
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

//    @Embedded
//    private Profile profile;

    @Column(name = "role", nullable = false)
    private int role; // 0: ADMIN, 1: USER

    @Version
    private Long version;

    public Member(String email, String password, String phoneNumber, int role) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public void updatePassword(UUID userId, String newPassword) {
        this.password = newPassword;
    }

    public void softDelete(UUID userId) {
        this.status = 99L;
    }
}