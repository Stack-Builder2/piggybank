package com.example.piggybank.domain.profile.service;

import com.example.piggybank.domain.profile.dto.ProfileReqDto;
import com.example.piggybank.domain.profile.entity.Profile;
import java.util.UUID;

public interface ProfileService {

    Profile updatePassword(ProfileReqDto request, String password);
}
