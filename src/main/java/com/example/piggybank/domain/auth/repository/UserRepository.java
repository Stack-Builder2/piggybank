package com.example.piggybank.domain.auth.repository;

import com.example.piggybank.domain.auth.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

}
