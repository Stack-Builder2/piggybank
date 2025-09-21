package com.example.piggybank.accountmanagement.domain.service.command;

import com.example.piggybank.accountmanagement.api.dto.request.CreateCategoryRequest;
import com.example.piggybank.accountmanagement.api.dto.request.DeleteCategoryRequest;
import com.example.piggybank.accountmanagement.api.dto.request.UpdateCategoryRequest;

public interface CategoryCommandService {

    void save(CreateCategoryRequest request);
    void updateCategory(UpdateCategoryRequest request);
    void deleteCategory(DeleteCategoryRequest request);
}
