package com.example.piggybank.membermanagement.api.controller;

import com.example.piggybank.membermanagement.api.dto.request.LoginRequest;
import com.example.piggybank.membermanagement.api.dto.request.SignUpRequest;
import com.example.piggybank.membermanagement.api.dto.request.UserUpdateRequest;
import com.example.piggybank.membermanagement.api.dto.response.TokenResponse;
import com.example.piggybank.membermanagement.domain.service.facade.MemberFacadeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("memberAccountController")
@RequestMapping("/api/v2/auth")
@RequiredArgsConstructor
public class MemberController {
    private final MemberFacadeServiceImpl memberService;

    @Operation(summary = "회원가입", description = "이메일, 패스워드, 연락처로 회원가입합니다.")
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest request) {
        memberService.signUp(request);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(memberService.login(request));
    }

    @Operation(summary = "비밀번호 변경", description = "사용자 비밀번호 변경")
    @PostMapping("/update")
    public ResponseEntity<String> updatePassword(@RequestBody UserUpdateRequest request) {
        memberService.changePassword(request);
        return ResponseEntity.ok("비밀번호 변경 성공");
    }

    @Operation(summary = "사용자 계정 삭제", description = "사용자 계정 삭제")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody String email) {
        memberService.softDeleteMember(email);
        return ResponseEntity.ok("소프트 삭제 성공");
    }
}