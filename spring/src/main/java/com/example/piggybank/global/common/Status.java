package com.example.piggybank.global.common;

import lombok.Getter;

@Getter
public enum Status {
    CREATED(1L),
    UPDATED(10L),
    DELETED(99L);

    private final Long status;

    Status(Long status) {
        this.status = status;
    }

}
