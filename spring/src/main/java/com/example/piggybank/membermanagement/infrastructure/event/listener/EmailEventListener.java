package com.example.piggybank.membermanagement.infrastructure.event.listener;

import com.example.piggybank.global.email.service.EmailService;
import com.example.piggybank.membermanagement.infrastructure.event.PasswordChangedEmailEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailEventListener {

    private final EmailService emailService;

    @EventListener
    public void handlePasswordChangeRequested(PasswordChangedEmailEvent event) {

        emailService.sendVerificationEmail(
            event.getTo(),
            event.getVerificationUrl()
        );

    }


}
