package com.example.piggybank.profilemanagement.domain.entity;

import static com.example.piggybank.global.common.Status.DELETED;

import com.example.piggybank.global.common.BaseTimeEntity;
import com.example.piggybank.global.common.Status;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profile")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "profile_id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID profileId;

    @Column(name = "user_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(nullable = false)
    private BigDecimal goal = BigDecimal.ZERO;

    @Column(nullable = false, name = "`limit`")
    private BigDecimal limit = BigDecimal.ZERO;

    @Version
    private Long version;

    @Builder
    public Profile(UUID profileId, UUID userId, BigDecimal goal, BigDecimal limit, Long version) {
        this.profileId = profileId;
        this.userId = userId;
        this.goal = goal;
        this.limit = limit;
        this.version = version;
    }

    public void delete(String email) {
        status = DELETED;
    }

    public void updateGoal(BigDecimal goal) {
        this.goal = goal;
    }

    public void updateLimit(BigDecimal limit) {
        this.limit = limit;
    }
}
