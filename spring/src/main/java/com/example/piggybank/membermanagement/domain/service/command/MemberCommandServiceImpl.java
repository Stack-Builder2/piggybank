package com.example.piggybank.membermanagement.domain.service.command;

import com.example.piggybank.global.security.JwtTokenProvider;
import com.example.piggybank.membermanagement.api.dto.response.TokenResponse;
import com.example.piggybank.membermanagement.domain.entity.Member;
import com.example.piggybank.membermanagement.infrastructure.repository.MemberRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public Member createMember(String email, String password, String phoneNumber, MemberRole role) {
        Member member = Member.builder()
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .role(1)
                .build();

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

    @Override
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

}