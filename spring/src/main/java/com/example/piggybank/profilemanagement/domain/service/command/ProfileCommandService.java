package com.example.piggybank.profilemanagement.domain.service.command;

import com.example.piggybank.profilemanagement.domain.entity.Profile;
import java.math.BigDecimal;
import java.util.UUID;

public interface ProfileCommandService {
    public void createProfile(UUID userId);
    public Profile updateLimit(UUID userId, BigDecimal limit);
    public Profile updateGoal(UUID userId, BigDecimal goal);
}
