package com.example.piggybank.accountmanagement.domain.service.query;

import com.example.piggybank.accountmanagement.domain.entity.Category;
import com.example.piggybank.accountmanagement.infrastructure.repository.CategoryRepository;
import com.example.piggybank.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Long getCategoryId(String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
            .orElse(new Category("기타"));

        return category.getCategoryId();
    }
}
