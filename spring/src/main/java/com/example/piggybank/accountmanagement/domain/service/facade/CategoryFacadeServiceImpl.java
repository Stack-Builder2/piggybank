package com.example.piggybank.accountmanagement.domain.service.facade;

import com.example.piggybank.accountmanagement.api.dto.request.CreateCategoryRequest;
import com.example.piggybank.accountmanagement.api.dto.request.DeleteCategoryRequest;
import com.example.piggybank.accountmanagement.api.dto.request.UpdateCategoryRequest;
import com.example.piggybank.accountmanagement.domain.service.command.CategoryCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryFacadeServiceImpl implements CategoryFacadeService {

    private final CategoryCommandService categoryCommandService;

    @Override
    public void createCategory(CreateCategoryRequest request) {

        categoryCommandService.save(request);
    }

    @Override
    public void updateCategory(UpdateCategoryRequest request) {

        categoryCommandService.updateCategory(request);
    }

    @Override
    public void deleteCategory(DeleteCategoryRequest request) {
        categoryCommandService.deleteCategory(request);
    }
}
