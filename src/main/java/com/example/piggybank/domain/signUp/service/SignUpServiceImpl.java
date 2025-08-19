package com.example.piggybank.domain.signUp.service;

import com.example.piggybank.domain.auth.entity.User;
import com.example.piggybank.domain.signUp.dto.req.SignUpRequest;
import com.example.piggybank.domain.signUp.repository.SignUpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final SignUpRepository signUpRepository;

    @Override
    public void signUp(SignUpRequest request) {
        // 기존 이메일 중복 체크
        signUpRepository.findByEmail(request.getEmail())
                .ifPresent(user -> { throw new IllegalArgumentException("이미 존재하는 이메일입니다."); });

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .ph(request.getPh())
                .version(0L) // 또는 null로 넘겨도 버전은 자동 증가됨
                .build();

        signUpRepository.save(user);
    }
}