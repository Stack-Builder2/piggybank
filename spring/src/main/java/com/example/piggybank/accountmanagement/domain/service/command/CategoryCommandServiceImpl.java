package com.example.piggybank.accountmanagement.domain.service.command;

import com.example.piggybank.accountmanagement.api.dto.request.CreateCategoryRequest;
import com.example.piggybank.accountmanagement.api.dto.request.DeleteCategoryRequest;
import com.example.piggybank.accountmanagement.api.dto.request.UpdateCategoryRequest;
import com.example.piggybank.accountmanagement.domain.entity.Category;
import com.example.piggybank.accountmanagement.infrastructure.repository.CategoryRepository;
import com.example.piggybank.global.error.ErrorCode;
import com.example.piggybank.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryCommandServiceImpl implements CategoryCommandService {

    private final CategoryRepository categoryRepository;

    @Override
    public void save(CreateCategoryRequest request) {
        Category category = Category.builder()
            .name(request.getCategoryName())
            .build();

        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(UpdateCategoryRequest request) {
        Category category = categoryRepository.findByName(request.getOldCategoryName())
            .orElseThrow(() -> new BusinessException("카테고리를 찾을 수 없습니다.", ErrorCode.CATEGORY_NOT_FOUND));

        category.updateCategoryName(request.getNewCategoryName());
    }

    @Override
    public void deleteCategory(DeleteCategoryRequest request) {
        Category category = categoryRepository.findByName(request.getCategoryName())
            .orElseThrow(() -> new BusinessException("카테고리를 찾을 수 없습니다.", ErrorCode.CATEGORY_NOT_FOUND));

        categoryRepository.delete(category);
    }
}
