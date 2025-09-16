package com.example.piggybank.membermanagement.domain.service.query;

import com.example.piggybank.membermanagement.domain.entity.Member;
import com.example.piggybank.membermanagement.infrastructure.repository.MemberRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;


    public Optional<Member> findById(UUID userId) {
        return memberRepository.findById(userId);
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
}