package com.example.piggybank.domain.profile.repository;

import com.example.piggybank.domain.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
