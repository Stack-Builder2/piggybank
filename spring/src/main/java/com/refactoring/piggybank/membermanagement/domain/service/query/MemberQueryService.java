package com.refactoring.piggybank.membermanagement.domain.service.query;

import com.refactoring.piggybank.membermanagement.api.dto.request.LoginRequest;
import com.refactoring.piggybank.membermanagement.api.dto.response.TokenResponse;
import com.refactoring.piggybank.membermanagement.domain.entity.Member;
import com.refactoring.piggybank.membermanagement.infrastructure.repository.MemberRepository;
import com.refactoring.piggybank.global.error.ErrorCode;
import com.refactoring.piggybank.global.error.exception.EntityNotFoundException;
import com.refactoring.piggybank.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponse login(final LoginRequest request) {
        final Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new BadCredentialsException("로그인에 실패하였습니다.");
        }

        final String token = jwtTokenProvider.createToken(member.getUserId(), member.getEmail());

        return new TokenResponse(
                token,
                "Bearer",
                member.getEmail()
        );
    }
}