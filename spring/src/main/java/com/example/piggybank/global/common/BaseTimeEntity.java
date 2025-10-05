package com.example.piggybank.global.common;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    
    @Column(name="created_at", insertable=false, updatable=false, nullable=false)
    private LocalDateTime createdAt;
    
    @Column(name="updated_at", insertable=false, updatable=false, nullable=false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name="status", insertable=false)
    protected Status status;
}
