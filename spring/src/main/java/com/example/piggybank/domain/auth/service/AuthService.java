package com.example.piggybank.domain.auth.service;

import com.example.piggybank.domain.auth.dto.req.LoginRequest;
import com.example.piggybank.domain.auth.dto.req.SignUpRequest;
import com.example.piggybank.domain.auth.dto.resp.TokenResponse;
import com.example.piggybank.domain.auth.dto.req.UserUpdateRequest;
import jakarta.validation.Valid;
import java.util.UUID;

public interface AuthService {

    public TokenResponse login(LoginRequest request);

    public void changePassword(UserUpdateRequest request);

    public void softDeleteUser(String email);

    public void signUp(SignUpRequest request);

}
