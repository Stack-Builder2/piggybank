package com.example.piggybank.global.codef.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public record CodefConnectedIdReqDto (
    @NotBlank(message = "accessToken이 비었습니다.")
    String accessToken,
    
    @NotBlank(message = "은행 아이디가 비었습니다.")
    String bankId,
    
    @NotBlank(message = "은행 비밀번호가 비었습니다.")
    String bankPassword,
    
    @NotBlank(message = "은행명을 입력해주세요.")
    String bankName
){

}
