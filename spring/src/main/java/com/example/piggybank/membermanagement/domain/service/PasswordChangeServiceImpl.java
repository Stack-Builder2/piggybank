package com.refactoring.piggybank.membermanagement.domain.service;

import static com.refactoring.piggybank.global.error.ErrorCode.*;

import com.example.piggybank.global.error.ErrorCode;
import com.example.piggybank.global.error.exception.EntityNotFoundException;
import com.refactoring.piggybank.global.error.exception.BusinessException;
import com.refactoring.piggybank.membermanagement.domain.entity.Member;
import com.refactoring.piggybank.membermanagement.domain.service.query.MemberQueryService;
import com.refactoring.piggybank.membermanagement.infrastructure.event.PasswordChangeSuccessEvent;
import com.refactoring.piggybank.membermanagement.infrastructure.event.PasswordChangedEmailEvent;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PasswordChangeServiceImpl implements PasswordChangeService {

    private final TokenService tokenService;
    private final ApplicationEventPublisher eventPublisher;
    private final MemberQueryService memberQueryService;

    @Override
    @Transactional
    public void requestReset(String email) {

        Member member = memberQueryService.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        String token = tokenService.saveTempToken(email, Duration.ofMinutes(15));

        String verificationUrl = "http://localhost:8080/api/v1/password/reset?token=" + token;
        eventPublisher.publishEvent(
            new PasswordChangedEmailEvent(
                this,
                email,
                "비밀번호 재설정 요청",
                verificationUrl,
                member.getUserId()
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

        eventPublisher.publishEvent(
            new PasswordChangeSuccessEvent(
                this,
                email,
                "비밀번호 변경 완료",
                null,
                member.getUserId()
            )
        );
    }
}
