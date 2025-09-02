package com.refactoring.piggybank.membermanagement.domain.service.command;

import com.example.piggybank.global.security.JwtTokenProvider;
import com.refactoring.piggybank.membermanagement.api.dto.request.SignUpRequest;
import com.refactoring.piggybank.membermanagement.api.dto.response.TokenResponse;
import com.refactoring.piggybank.membermanagement.domain.entity.Member;
import com.refactoring.piggybank.membermanagement.domain.entity.Profile;
import com.refactoring.piggybank.membermanagement.infrastructure.repository.MemberRepository;
import com.refactoring.piggybank.global.error.ErrorCode;
import com.refactoring.piggybank.global.error.exception.EntityNotFoundException;
import java.util.Collections;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public Member createMember(UUID userId) {
        Member member = new Member(userId);

        return memberRepository.save(member);
    }

    public TokenResponse generateToken(UUID userId, String email) {
        String token = jwtTokenProvider.createToken(
            userId,
            email
        );
        return new TokenResponse(
            token,
            "Bearer",
            email
        );
    }

    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

}