package com.example.piggybank.membermanagement.domain.service.query;

import com.example.piggybank.membermanagement.domain.entity.Member;
import com.example.piggybank.membermanagement.infrastructure.repository.MemberRepository;
import com.example.piggybank.global.security.JwtTokenProvider;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    public Optional<Member> findById(UUID userId) {
        return memberRepository.findById(userId);
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public boolean existByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
}