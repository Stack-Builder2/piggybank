package com.example.piggybank.domain.auth.controller;

import com.example.piggybank.domain.auth.dto.req.LoginRequest;
import com.example.piggybank.domain.auth.dto.req.UserUpdateRequest;
import com.example.piggybank.domain.auth.dto.resp.TokenResponse;
import com.example.piggybank.domain.auth.service.AuthService;
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
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @Operation(summary = "비밀번호 변경", description = "사용자 비밀번호 변경")
    @PostMapping("/update")
    public ResponseEntity<String> updatePassword(UserUpdateRequest request) {
        authService.changePassword(request);
        return ResponseEntity.ok("비밀번호 변경 성공");
    }

    @Operation(summary = "사용자 계정 삭제", description = "사용자 계정 삭제")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(String email) {
        authService.softDeleteUser(email);
        return ResponseEntity.ok("소프트 삭제 성공");
    }
}
