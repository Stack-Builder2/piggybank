package com.refactoring.piggybank.global.email.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Schema(description = "이메일 요청 DTO")
public record EmailRequest(
    @NotBlank(message = "수신자 이메일은 필수입니다.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    @Schema(description = "수신자 이메일 주소", example = "test@example.com")
    String to,

    @Schema(description = "이메일 제목", example = "test")
    @NotBlank(message = "제목은 필수입니다.")
    String subject,

    @Schema(description = "이메일 내용", example = "https://www.naver.com")
    @NotBlank(message = "내용은 필수입니다.")
    String verificationUrl
) {

}
