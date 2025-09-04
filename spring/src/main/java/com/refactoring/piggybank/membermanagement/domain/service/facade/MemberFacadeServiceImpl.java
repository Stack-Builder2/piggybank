package com.refactoring.piggybank.membermanagement.domain.service.facade;

import static com.refactoring.piggybank.global.error.ErrorCode.*;

import com.example.piggybank.global.error.ErrorCode;
import com.example.piggybank.global.error.exception.EntityNotFoundException;
import com.refactoring.piggybank.global.error.exception.BusinessException;
import com.refactoring.piggybank.membermanagement.api.dto.request.LoginRequest;
import com.refactoring.piggybank.membermanagement.api.dto.request.SignUpRequest;
import com.refactoring.piggybank.membermanagement.api.dto.request.UserUpdateRequest;
import com.refactoring.piggybank.membermanagement.api.dto.response.TokenResponse;
import com.refactoring.piggybank.membermanagement.domain.entity.Member;
import com.refactoring.piggybank.membermanagement.domain.entity.Profile;
import com.refactoring.piggybank.membermanagement.domain.service.command.MemberCommandService;
import com.refactoring.piggybank.membermanagement.domain.service.command.MemberCommandServiceImpl;
import com.refactoring.piggybank.membermanagement.domain.service.query.MemberQueryService;
import com.refactoring.piggybank.membermanagement.domain.service.query.MemberQueryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberFacadeServiceImpl {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @Transactional(readOnly = true)
    public TokenResponse login(LoginRequest request) {

        Member member = memberQueryService.findByEmail(request.email())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        if(!memberCommandService.validatePassword(request.password(), member.getPassword())) {
            throw new BadCredentialsException("로그인에 실패하였습니다.");
        }

        TokenResponse token = memberCommandService.generateToken(
            member.getUserId(),
            member.getEmail()
        );

        // 회원정보로 토큰 응답 완성
        return new TokenResponse(
            token.accessToken(),
            token.tokenType(),
            member.getEmail()
        );
    }

    @Transactional(rollbackFor = Exception.class)
    public void changePassword(UserUpdateRequest request) {
        Member member = memberQueryService.findById(request.userId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        String newPassword = memberCommandService.encodePassword(request.password());

        member.updatePassword(member.getUserId(), newPassword);
    }

    @Transactional(rollbackFor = Exception.class)
    public void softDeleteMember(String email) {
        Member member = memberQueryService.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        member.softDelete(member.getUserId());
    }

    @Transactional
    public void signUp(SignUpRequest request) {

        if(memberQueryService.existByEmail(request.email())) {
            throw new BusinessException(EMAIL_DUPLICATION);
        }

        String encodePassword = memberCommandService.encodePassword(request.password());

        // 프로필 정보 초기화
        final Profile newProfile = new Profile();
        newProfile.setGoal("0");
        newProfile.setLimit("0");

        final Member member = Member.builder()
            .email(request.email())
            .password(encodePassword)
            .phoneNumber(request.phoneNumber())
//            .profile(newProfile)
            .role(1) // USER 역할
            .build();

        memberCommandService.createMember(member.getUserId());
    }
}
