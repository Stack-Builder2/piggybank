package com.refactoring.piggybank.membermanagement.domain.service.command;

import com.refactoring.piggybank.membermanagement.api.dto.response.TokenResponse;
import com.refactoring.piggybank.membermanagement.domain.entity.Member;
import java.util.UUID;

public interface MemberCommandService {

    Member createMember(String email, String password, String phoneNumber, int role);
    TokenResponse generateToken(UUID userId, String email);
    boolean validatePassword(String rawPassword, String encodedPassword);
    String encodePassword(String rawPassword);

}
