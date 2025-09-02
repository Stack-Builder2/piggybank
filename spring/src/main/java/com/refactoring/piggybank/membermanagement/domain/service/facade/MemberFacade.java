package com.refactoring.piggybank.membermanagement.domain.service.facade;

import static com.refactoring.piggybank.global.error.ErrorCode.*;

import com.example.piggybank.domain.auth.dto.req.LoginRequest;
import com.example.piggybank.domain.auth.dto.req.UserUpdateRequest;
import com.example.piggybank.domain.auth.dto.resp.TokenResponse;
import com.example.piggybank.domain.auth.entity.User;
import com.example.piggybank.domain.profile.entity.Profile;
import com.example.piggybank.global.error.ErrorCode;
import com.example.piggybank.global.error.exception.EntityNotFoundException;
import com.refactoring.piggybank.global.error.exception.BusinessException;
import com.refactoring.piggybank.membermanagement.api.dto.request.SignUpRequest;
import com.refactoring.piggybank.membermanagement.domain.entity.Member;
import com.refactoring.piggybank.membermanagement.domain.service.command.MemberCommandService;
import com.refactoring.piggybank.membermanagement.domain.service.query.MemberQueryService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @Transactional(readOnly = true)
    public TokenResponse login(LoginRequest request) {

        Member member = memberQueryService.findByEmail(request.email())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        if(!memberCommandService.validatePassword(request.password(), member.getPassword())) {
            throw new BadCredentialsException("로그인에 실패하였습니다.");
        }

        final com.refactoring.piggybank.membermanagement.api.dto.response.TokenResponse token = memberCommandService.generateToken(member.getUserId(),
            member.getEmail());

        return new TokenResponse(
            token,
            "Bearer",
            member.getEmail()
        );
    }

    @Transactional(rollbackFor = Exception.class)
    public void changePassword(UserUpdateRequest request) {
        User user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        String newPassword = passwordEncoder.encode(request.password());

        user.updatePassword(request.email(), newPassword);
    }

    @Transactional(rollbackFor = Exception.class)
    public void softDeleteUser(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        Profile profile = profileRepository.findByUserId(user.getUserId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        user.delete(email);
        profile.delete(email);
    }

    @Transactional
    public void signUp(SignUpRequest request) {

        if(memberQueryService.existByEmail(request.email())) {
            throw new BusinessException(EMAIL_DUPLICATION);
        }

        String encodePassword = memberCommandService.encodePassword(request.password());

        // 프로필 정보 초기화
        final com.refactoring.piggybank.membermanagement.domain.entity.Profile newProfile = new com.refactoring.piggybank.membermanagement.domain.entity.Profile();
        newProfile.setGoal("0");
        newProfile.setLimit("0");

        final Member member = Member.builder()
            .email(request.email())
            .password(encodePassword)
            .phoneNumber(request.phoneNumber())
            .profile(newProfile)
            .role(1) // USER 역할
            .build();

        memberCommandService.createMember(member.getUserId());
    }
}
