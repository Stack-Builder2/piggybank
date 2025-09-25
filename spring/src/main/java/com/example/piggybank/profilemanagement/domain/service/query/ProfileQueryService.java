package com.example.piggybank.profilemanagement.domain.service.query;

import com.example.piggybank.profilemanagement.domain.entity.Profile;
import java.util.UUID;

public interface ProfileQueryService {
    public Profile findByUserId(UUID userId);
}
