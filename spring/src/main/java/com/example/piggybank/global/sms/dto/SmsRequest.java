package com.refactoring.piggybank.global.sms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "sms 요청 DTO")
public record SmsRequest(
    @NotBlank(message = "발신자 휴대전화번호는 필수입니다.")
    @Schema(description = "발신자 휴대전화번호 주소", example = "01012345678")
    String from,

    @NotBlank(message = "수신자 휴대전화번호는 필수입니다.")
    @Schema(description = "수신자 휴대전화번호 주소", example = "01012345678")
    String to,

    @Schema(description = "메시지 타입", example = "SMS")
    @NotBlank(message = "메시지 타입은 필수입니다.")
    String type,

    @Schema(description = "메시지 내용", example = "test 메시지 입니다.")
    @NotBlank(message = "메시지 내용은 필수입니다.")
    String text,

    @Schema(description = "SMS API KEY", example = "apiKey")
    String apiKey,

    @Schema(description = "SMS SECRET API KEY", example = "secretKey")
    String apiSecret
) {

}