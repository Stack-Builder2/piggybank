package com.example.piggybank.domain.signUp.controller;

import com.example.piggybank.domain.signUp.dto.req.SignUpRequest;
import com.example.piggybank.domain.signUp.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    public ResponseEntity<String> signUp(@RequestBody SignUpRequest request) {
        signUpService.signUp(request);
        return ResponseEntity.ok("회원가입 성공");
    }
}