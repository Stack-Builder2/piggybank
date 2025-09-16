package com.example.piggybank.membermanagement.infrastructure.event;

import lombok.Getter;

@Getter
public class PasswordChangedEmailEvent extends EmailEvent {

    public PasswordChangedEmailEvent(Object source, String to, String verificationUrl) {
        super(source, to, verificationUrl);
    }
}
