package com.example.piggybank.accountmanagement.domain.entity;

import static com.example.piggybank.global.error.ErrorCode.CATEGORY_NOT_FOUND;

import com.example.piggybank.global.common.BaseTimeEntity;
import com.example.piggybank.global.error.ErrorCode;
import com.example.piggybank.global.error.exception.BusinessException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "CategoryManagement")
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long categoryId;
    
    @Column(nullable = false)
    private String name;
    
    @Version
    private Long version;
    
    @Builder
    public Category(String name) {
        this.name = name;
    }

    public static Long requireNonNullCategoryId(Long categoryId) {
        if(categoryId == null) {
            throw new BusinessException(CATEGORY_NOT_FOUND);
        }
        return categoryId;
    }

    public void updateCategoryId(Long categoryId) {
        this.categoryId = requireNonNullCategoryId(categoryId);
    }

    public void updateCategoryName(String categoryName) {
        name = categoryName;
    }

}
