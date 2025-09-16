package com.example.piggybank.membermanagement.api.controller;

import com.example.piggybank.membermanagement.domain.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/view/password")
@RequiredArgsConstructor
public class PasswordViewController {

    private final TokenService tokenService;

    @Operation(summary = "토큰 검증", description = "비밀번호 변경을 위한 토큰 검증")
    @GetMapping("/reset/verify")
    public String verifyToken(@RequestParam("token") String token, Model model) {

        boolean isValid = tokenService.exists(token);

        if (!isValid) {
            model.addAttribute("message", "유효하지 않거나 만료된 토큰입니다.");
            return "error-page";
        }

        model.addAttribute("token", token);
        return "password-reset-form";
    }

}
