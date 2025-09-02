package com.refactoring.piggybank.membermanagement.domain.service.query;

import com.refactoring.piggybank.membermanagement.api.dto.request.LoginRequest;
import com.refactoring.piggybank.membermanagement.api.dto.response.TokenResponse;
import com.refactoring.piggybank.membermanagement.domain.entity.Member;
import com.refactoring.piggybank.membermanagement.infrastructure.repository.MemberRepository;
import com.refactoring.piggybank.global.error.ErrorCode;
import com.refactoring.piggybank.global.error.exception.EntityNotFoundException;
import com.refactoring.piggybank.global.security.JwtTokenProvider;
import java.util.Optional;
import java.util.UUID;
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


    public Optional<Member> findByMemberId(UUID userId) {
        return memberRepository.findById(userId);
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public boolean existByEmail(String email) {
        return memberRepository.existByEmail(email);
    }
}