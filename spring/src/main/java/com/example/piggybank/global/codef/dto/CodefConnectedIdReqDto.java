package com.example.piggybank.global.codef.dto;

import com.example.piggybank.accountmanagement.domain.entity.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CodefConnectedIdReqDto (
    @NotBlank(message = "accessToken이 비었습니다.")
    String accessToken,
    
    @NotBlank(message = "은행 아이디가 비었습니다.")
    String bankId,
    
    @NotNull
    @NotBlank(message = "은행 비밀번호가 비었습니다.")
    String bankPassword,
    
    @NotBlank(message = "계좌가 비었습니다.")
    Account account
){

}
