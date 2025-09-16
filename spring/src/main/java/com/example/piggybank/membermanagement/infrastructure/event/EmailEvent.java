package com.example.piggybank.membermanagement.infrastructure.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public abstract class EmailEvent extends ApplicationEvent {

    private final String to;
    private final String verificationUrl;

    public EmailEvent(Object source, String email, String verificationUrl) {
        super(source);
        to = email;
        this.verificationUrl = verificationUrl;
    }

}
