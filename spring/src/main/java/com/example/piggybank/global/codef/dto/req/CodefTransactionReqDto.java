package com.example.piggybank.global.codef.dto.req;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record CodefTransactionReqDto(
    
    @NotBlank(message = "토큰이 비었습니다.")
    String accessToken,
    
    @NotBlank(message = "커넥티드 아이디가 비었습니다.")
    String connectedId,
    
    @NotBlank(message = "계좌Id가 비었습니다.")
    UUID accountId,
    
    @NotBlank(message = "은행명이 비었습니다.")
    String organization,
    
    @NotBlank(message = "계좌번호가 비었습니다.")
    String accountNumber,
    
    @NotBlank(message = "시작일이 비었습니다.")
    String startDate,
    
    @NotBlank(message = "마감일이 비었습니다.")
    String endDate
    
) {




}
