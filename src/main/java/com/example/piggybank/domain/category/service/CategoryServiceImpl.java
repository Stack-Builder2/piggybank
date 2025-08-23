package com.example.piggybank.domain.category.service;

import com.example.piggybank.domain.category.dto.req.CreateCategoryReqDto;
import com.example.piggybank.domain.category.dto.req.DeleteCategoryReqDto;
import com.example.piggybank.domain.category.dto.req.UpdateCategoryReqDto;
import com.example.piggybank.domain.category.entity.Category;
import com.example.piggybank.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    @Override
    public void createCategory(CreateCategoryReqDto reqDto) {
        
        Category category = Category.builder().name(reqDto.getCategoryName()).build();
        categoryRepository.save(category);
        
    }
    
    @Override
    public void updateCategory(UpdateCategoryReqDto reqDto) {
        
        Category category = categoryRepository.findByName(reqDto.getOldCategoryName());
        category.setName(reqDto.getNewCategoryName());
        categoryRepository.save(category);
        
    }
    
    @Override
    public void deleteCategory(DeleteCategoryReqDto reqDto) {
        Category category = categoryRepository.findByName(reqDto.getCategoryName());
        categoryRepository.delete(category);
    }
    
    
}
