package com.example.piggybank.domain.signUp.service;

import com.example.piggybank.domain.signUp.dto.req.SignUpRequest;

public interface SignUpService {
    void signUp(SignUpRequest request);
}