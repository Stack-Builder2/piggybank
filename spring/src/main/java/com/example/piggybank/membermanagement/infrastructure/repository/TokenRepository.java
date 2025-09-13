package com.example.piggybank.membermanagement.infrastructure.repository;

import com.example.piggybank.membermanagement.domain.entity.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, String> {

}
