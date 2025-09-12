package com.refactoring.piggybank.membermanagement.infrastructure.repository;

import com.refactoring.piggybank.membermanagement.domain.entity.Member;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findByEmail(String email);
    boolean existByEmail(String email);
}