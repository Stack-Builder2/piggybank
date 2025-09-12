package com.refactoring.piggybank.membermanagement.infrastructure.event.listener;

import com.refactoring.piggybank.global.email.service.EmailService;
import com.refactoring.piggybank.membermanagement.infrastructure.event.PasswordChangeSuccessEvent;
import com.refactoring.piggybank.membermanagement.infrastructure.event.PasswordChangedEmailEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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
            "비밀번호 재설정 요청",
            event.getVerificationUrl()
        );

    }


}
