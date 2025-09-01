package com.refactoring.piggybank.membermanagement.domain.service.command;

import com.refactoring.piggybank.membermanagement.api.dto.request.SignUpRequest;
import com.refactoring.piggybank.membermanagement.domain.entity.Member;
import com.refactoring.piggybank.membermanagement.domain.entity.Profile;
import com.refactoring.piggybank.membermanagement.infrastructure.repository.MemberRepository;
import com.refactoring.piggybank.global.error.ErrorCode;
import com.refactoring.piggybank.global.error.exception.EntityNotFoundException;
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

    public void signUp(final SignUpRequest request) {
        memberRepository.findByEmail(request.getEmail())
                .ifPresent(member -> {
                    throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
                });

        // 프로필 정보 초기화
        final Profile newProfile = new Profile();
        newProfile.setGoal("0");
        newProfile.setLimit("0");

        final Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .profile(newProfile)
                .role(1) // USER 역할
                .build();

        memberRepository.save(member);
    }

    public void changePassword(final String email, final String newPassword) {
        final Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        final String encodedPassword = passwordEncoder.encode(newPassword);
        member.updatePassword(encodedPassword);
    }

    public void softDeleteMember(final String email) {
        final Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
        member.softDelete();
    }
}