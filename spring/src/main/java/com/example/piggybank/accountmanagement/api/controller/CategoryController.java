package com.example.piggybank.accountmanagement.api.controller;

import com.example.piggybank.accountmanagement.api.dto.request.CreateCategoryRequest;
import com.example.piggybank.accountmanagement.api.dto.request.DeleteCategoryRequest;
import com.example.piggybank.accountmanagement.api.dto.request.UpdateCategoryRequest;
import com.example.piggybank.accountmanagement.domain.service.facade.CategoryFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("managementCategoryController")
@RequestMapping("/api/v2/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryFacadeService categoryService;

    @Operation(summary = "카테고리 추가", description = "소비 및 지출 카테고리 추가")
    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody CreateCategoryRequest request) {
        categoryService.createCategory(request);
        return ResponseEntity.ok("create category success");
    }

    @Operation(summary="카테고리명 수정", description = "소비 및 지출 카테고리명 변경")
    @PostMapping("/update")
    public ResponseEntity<String> updateCategory(@RequestBody UpdateCategoryRequest request) {
        categoryService.updateCategory(request);
        return ResponseEntity.ok("category updated");
    }

    @Operation(summary="카테고리 삭제", description = "소비 및 지출 카테고리 삭제")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCategory(@RequestBody DeleteCategoryRequest request){

        categoryService.deleteCategory(request);
        return ResponseEntity.ok("categoryDeleted");
    }

}
