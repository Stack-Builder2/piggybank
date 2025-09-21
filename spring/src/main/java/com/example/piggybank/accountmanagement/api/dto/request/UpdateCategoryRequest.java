package com.example.piggybank.accountmanagement.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCategoryRequest {
    @NotBlank
    @JsonProperty("oldCategoryName")
    String oldCategoryName;

    @NotBlank
    @JsonProperty("newCategoryName")
    String newCategoryName;
}