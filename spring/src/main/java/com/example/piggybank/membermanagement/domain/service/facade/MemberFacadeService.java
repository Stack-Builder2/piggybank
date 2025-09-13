package com.example.piggybank.membermanagement.domain.service.facade;

import com.example.piggybank.membermanagement.api.dto.request.LoginRequest;
import com.example.piggybank.membermanagement.api.dto.request.SignUpRequest;
import com.example.piggybank.membermanagement.api.dto.response.TokenResponse;

public interface MemberFacadeService {

    TokenResponse login(LoginRequest request);
    void softDeleteMember(String email);
    void signUp(SignUpRequest request);

}
