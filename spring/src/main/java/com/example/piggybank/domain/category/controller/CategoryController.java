package com.example.piggybank.domain.category.controller;

import com.example.piggybank.domain.category.dto.req.CreateCategoryReqDto;
import com.example.piggybank.domain.category.dto.req.DeleteCategoryReqDto;
import com.example.piggybank.domain.category.dto.req.UpdateCategoryReqDto;
import com.example.piggybank.domain.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    
    @Operation(summary = "카테고리 추가", description = "소비 및 지출 카테고리 추가")
    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody CreateCategoryReqDto createCategoryReqDto) {
        categoryService.createCategory(createCategoryReqDto);
        return ResponseEntity.ok("create category success");
    }
    
    @Operation(summary="카테고리명 수정", description = "소비 및 지출 카테고리명 변경")
    @PostMapping("/update")
    public ResponseEntity<String> updateCategory(@RequestBody UpdateCategoryReqDto updateCategoryReqDto) {
        categoryService.updateCategory(updateCategoryReqDto);
        return ResponseEntity.ok("category updated");
    }
    
    @Operation(summary="카테고리 삭제", description = "소비 및 지출 카테고리 삭제")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCategory(@RequestBody DeleteCategoryReqDto deleteCategoryReqDto){
        
        categoryService.deleteCategory(deleteCategoryReqDto);
        return ResponseEntity.ok("categoryDeleted");
    }
    
    
    
}
