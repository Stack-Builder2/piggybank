package com.example.piggybank.accountmanagement.infrastructure.repository;


import com.example.piggybank.accountmanagement.domain.entity.Category;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findByName(String categoryName);
}