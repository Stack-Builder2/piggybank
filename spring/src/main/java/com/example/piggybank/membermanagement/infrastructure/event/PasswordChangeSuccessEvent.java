package com.example.piggybank.membermanagement.infrastructure.event;

import lombok.Getter;

@Getter
public class PasswordChangeSuccessEvent extends EmailEvent {

    public PasswordChangeSuccessEvent(Object source, String email,
        String verificationUrl) {
        super(source, email, verificationUrl);
    }
}
