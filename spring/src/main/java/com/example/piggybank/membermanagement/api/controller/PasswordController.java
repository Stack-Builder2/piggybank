package com.example.piggybank.membermanagement.api.controller;

import com.example.piggybank.membermanagement.api.dto.request.PasswordChangeRequest;
import com.example.piggybank.membermanagement.domain.service.PasswordChangeService;
import com.example.piggybank.membermanagement.domain.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/password")
public class PasswordController {

    private final TokenService tokenService;
    private final PasswordChangeService passwordChangeService;

    @Operation(summary = "이메일 발송", description = "비밀번호 변경을 위한 이메일 발송")
    @PostMapping("/email")
    public ResponseEntity<String> sendPasswordResetEmail(@RequestParam String email) {
        passwordChangeService.requestResetPassword(email);
        return ResponseEntity.ok("비밀번호 변경 인증 이메일이 발송되었습니다.");
    }

    @Operation(summary = "토큰 검증 (API)", description = "Swagger에서 토큰 유효성을 확인")
    @GetMapping("/reset/verify")
    public ResponseEntity<String> verifyToken(@RequestParam String token) {
        boolean isValid = tokenService.exists(token);

        if (!isValid) {
            return ResponseEntity.badRequest().body("유효하지 않거나 만료된 토큰입니다.");
        }
        return ResponseEntity.ok("토큰이 유효합니다.");
    }

    @Operation(summary = "비밀번호 변경", description = "토큰이 유효할 경우 새로운 비밀번호로 변경")
    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@ModelAttribute PasswordChangeRequest request) {

        passwordChangeService.resetPassword(request.token(), request.password());
        return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
    }

}
