package com.refactoring.piggybank.membermanagement.infrastructure.event;

import java.util.UUID;
import lombok.Getter;

@Getter
public class PasswordChangeSuccessEvent extends EmailEvent {

    private final UUID userId;

    public PasswordChangeSuccessEvent(Object source, String email, String subject,
        String verificationUrl, UUID userId) {
        super(source, email, subject, verificationUrl);
        this.userId = userId;
    }
}
