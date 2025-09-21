package com.example.piggybank.accountmanagement.domain.entity;

import com.example.piggybank.global.common.BaseTimeEntity;
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

    public void updateCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void updateCategoryName(String categoryName) {
        name = categoryName;
    }

}
