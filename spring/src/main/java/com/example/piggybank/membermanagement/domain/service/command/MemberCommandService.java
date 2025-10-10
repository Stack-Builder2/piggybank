package com.example.piggybank.membermanagement.domain.service.command;

import com.example.piggybank.membermanagement.api.dto.response.TokenResponse;
import com.example.piggybank.membermanagement.domain.entity.Member;
import com.example.piggybank.membermanagement.domain.entity.MemberRole;
import java.util.UUID;

public interface MemberCommandService {

    Member createMember(String email, String password, String phoneNumber, MemberRole role);
    TokenResponse generateToken(UUID userId, String email);
    boolean validatePassword(String rawPassword, String encodedPassword);
    String encodePassword(String rawPassword);

}