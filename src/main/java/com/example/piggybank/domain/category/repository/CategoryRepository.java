package com.example.piggybank.domain.category.repository;

import com.example.piggybank.domain.category.entity.Category;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    
    Category findByName(String categoryName);
}
