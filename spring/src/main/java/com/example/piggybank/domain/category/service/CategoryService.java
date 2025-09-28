package com.example.piggybank.domain.category.service;

import com.example.piggybank.domain.category.dto.req.CreateCategoryReqDto;
import com.example.piggybank.domain.category.dto.req.DeleteCategoryReqDto;
import com.example.piggybank.domain.category.dto.req.UpdateCategoryReqDto;
import com.example.piggybank.domain.category.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    public void createCategory(CreateCategoryReqDto reqDto);
    public void updateCategory(UpdateCategoryReqDto reqDto);
    public void deleteCategory(DeleteCategoryReqDto reqDto);
}
