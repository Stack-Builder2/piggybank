package com.refactoring.piggybank.membermanagement.api.controller;

import com.refactoring.piggybank.membermanagement.api.dto.request.LoginRequest;
import com.refactoring.piggybank.membermanagement.api.dto.request.SignUpRequest;
import com.refactoring.piggybank.membermanagement.api.dto.request.UserUpdateRequest;
import com.refactoring.piggybank.membermanagement.api.dto.response.TokenResponse;
import com.refactoring.piggybank.membermanagement.domain.service.command.MemberCommandService;
import com.refactoring.piggybank.membermanagement.domain.service.query.MemberQueryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @Operation(summary = "회원가입", description = "이메일, 패스워드, 연락처로 회원가입합니다.")
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest request) {
        memberCommandService.signUp(request);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(memberQueryService.login(request));
    }

    @Operation(summary = "비밀번호 변경", description = "사용자 비밀번호 변경")
    @PostMapping("/update")
    public ResponseEntity<String> updatePassword(@RequestBody String email, @RequestBody String newPassword) {
        memberCommandService.changePassword(email, newPassword);
        return ResponseEntity.ok("비밀번호 변경 성공");
    }

    @Operation(summary = "사용자 계정 삭제", description = "사용자 계정 삭제")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody String email) {
        memberCommandService.softDeleteMember(email);
        return ResponseEntity.ok("소프트 삭제 성공");
    }
}