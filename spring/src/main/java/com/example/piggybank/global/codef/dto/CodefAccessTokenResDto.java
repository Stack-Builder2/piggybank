package com.example.piggybank.global.codef.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema()
public record CodefAccessTokenResDto(
    
    @NotBlank(message = "accessToken이 비었습니다.")
    String accessToken
    
) {

}
