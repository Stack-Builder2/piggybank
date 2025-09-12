package com.refactoring.piggybank.membermanagement.infrastructure.repository;

import com.refactoring.piggybank.membermanagement.domain.entity.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, String> {

}
