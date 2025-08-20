package com.example.piggybank.domain.auth.service;

import com.example.piggybank.domain.auth.dto.req.LoginRequest;
import com.example.piggybank.domain.auth.dto.req.UserUpdateRequest;
import com.example.piggybank.domain.auth.dto.resp.TokenResponse;
import com.example.piggybank.domain.auth.entity.User;
import com.example.piggybank.domain.auth.repository.UserRepository;
import com.example.piggybank.global.error.ErrorCode;
import com.example.piggybank.global.error.exception.EntityNotFoundException;
import com.example.piggybank.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    
    @Transactional(readOnly = true)
    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
        
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("로그인에 실패하였습니다.");
        }
        
        String token = jwtTokenProvider.createToken(
            user.getEmail()
        );
        
        return new TokenResponse(
            token,
            "Bearer",
            user.getEmail()
        );
        
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(UserUpdateRequest request) {
        User user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
        
        String newPassword = passwordEncoder.encode(request.password());
        
        user.updatePassword(request.email(), newPassword);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void softDeleteUser(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
        
        user.delete(email);
    }
}
