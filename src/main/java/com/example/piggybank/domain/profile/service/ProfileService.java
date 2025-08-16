package com.example.piggybank.domain.profile.service;

import com.example.piggybank.domain.profile.dto.req.ProfileRequest;
import com.example.piggybank.domain.profile.entity.Profile;

public interface ProfileService {

    Profile updatePassword(ProfileRequest request, String password);
}
