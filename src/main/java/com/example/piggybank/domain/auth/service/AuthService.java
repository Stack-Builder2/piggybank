package com.example.piggybank.domain.auth.service;

import com.example.piggybank.domain.auth.dto.req.LoginRequest;
import com.example.piggybank.domain.auth.dto.resp.TokenResponse;
import com.example.piggybank.domain.auth.dto.req.UserUpdateRequest;

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

    @Transactional
    public void signUp(SignUpRequest request) {
        userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> { throw new IllegalArgumentException("이미 존재하는 이메일입니다."); });

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .ph(request.getPh())
                .version(0L)
                .build();

        userRepository.save(user);
    }
public interface AuthService {

    public TokenResponse login(LoginRequest request);

    public void changePassword(UserUpdateRequest request);

    public void softDeleteUser(String email);
}
