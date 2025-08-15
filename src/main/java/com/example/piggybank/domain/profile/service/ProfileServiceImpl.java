package com.example.piggybank.domain.profile.service;

import com.example.piggybank.domain.auth.entity.User;
import com.example.piggybank.domain.auth.repository.UserRepository;
import com.example.piggybank.domain.profile.dto.ProfileReqDto;
import com.example.piggybank.domain.profile.entity.Profile;
import com.example.piggybank.domain.profile.repository.ProfileRepository;
import com.example.piggybank.global.error.exception.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Override
    public Profile updatePassword(ProfileReqDto request, String password) {

        User user = userRepository.findById(request.userId())
            .orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다."));

        

        return null;
    }
}
