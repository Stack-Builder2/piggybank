package com.example.piggybank.membermanagement.infrastructure.event;

import java.util.UUID;
import lombok.Getter;

@Getter
public class PasswordChangedEmailEvent extends EmailEvent {

    private final UUID userId;

    public PasswordChangedEmailEvent(Object source, String to, String subject, String verificationUrl, UUID userId) {
        super(source, to, subject, verificationUrl);
        this.userId = userId;
    }
}
