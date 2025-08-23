package com.example.piggybank.domain.category.controller;

import com.example.piggybank.global.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final JwtTokenProvider jwtTokenProvider;
    
    @Operation(summary = "카테고리 추가", description = "소비 및 지출 카테고리 추가")
    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody String categoryName) {
        System.out.println(categoryName);
        return ResponseEntity.ok(categoryName);
    }
    
    
}
