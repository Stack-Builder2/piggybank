package com.example.piggybank.accountmanagement.domain.service.facade;

import com.example.piggybank.accountmanagement.api.dto.request.CreateCategoryRequest;
import com.example.piggybank.accountmanagement.api.dto.request.DeleteCategoryRequest;
import com.example.piggybank.accountmanagement.api.dto.request.UpdateCategoryRequest;

public interface CategoryFacadeService {
    public void createCategory(CreateCategoryRequest request);
    public void updateCategory(UpdateCategoryRequest request);
    public void deleteCategory(DeleteCategoryRequest request);
}
