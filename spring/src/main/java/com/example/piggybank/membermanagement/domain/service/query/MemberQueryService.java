package com.example.piggybank.membermanagement.domain.service.query;

import com.example.piggybank.membermanagement.domain.entity.Member;
import java.util.Optional;
import java.util.UUID;

public interface MemberQueryService {

    Optional<Member> findById(UUID userId);
    Optional<Member> findByEmail(String email);
    boolean existByEmail(String email);
}
