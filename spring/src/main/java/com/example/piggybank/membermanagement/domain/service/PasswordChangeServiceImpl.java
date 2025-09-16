package com.example.piggybank.membermanagement.domain.service;

import static com.example.piggybank.global.error.ErrorCode.*;

import com.example.piggybank.global.error.ErrorCode;
import com.example.piggybank.global.error.exception.EntityNotFoundException;
import com.example.piggybank.global.error.exception.BusinessException;
import com.example.piggybank.membermanagement.domain.entity.Member;
import com.example.piggybank.membermanagement.domain.service.query.MemberQueryService;
import com.example.piggybank.membermanagement.infrastructure.event.PasswordChangeSuccessEvent;
import com.example.piggybank.membermanagement.infrastructure.event.PasswordChangedEmailEvent;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PasswordChangeServiceImpl implements PasswordChangeService {

    private final TokenService tokenService;
    private final ApplicationEventPublisher eventPublisher;
    private final MemberQueryService memberQueryService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void requestResetPassword(String email) {

        String token = tokenService.saveTempToken(email, Duration.ofMinutes(15));

        String verificationUrl = "http://localhost:8080/view/password/reset/verify?token=" + token;

        eventPublisher.publishEvent(
            new PasswordChangedEmailEvent(
                this,
                email,
                verificationUrl
            )
        );
    }

    @Override
    @Transactional
    public void resetPassword(String token, String newPassword) {

        String email = tokenService.consume(token);
        if(email == null) {
            throw new BusinessException(INVALID_TOKEN);
        }

        Member member = memberQueryService.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        member.updatePassword(passwordEncoder.encode(newPassword));

        eventPublisher.publishEvent(
            new PasswordChangeSuccessEvent(
                this,
                email,
                null
            )
        );
    }
}
