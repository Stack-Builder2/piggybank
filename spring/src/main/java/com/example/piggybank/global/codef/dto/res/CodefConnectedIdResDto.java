package com.example.piggybank.global.codef.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CodefConnectedIdResDto(
    @JsonProperty("result") Result result,
    @JsonProperty("data") Data data
) {
    // 최상위 result
    public record Result(
        @JsonProperty("code") String code,
        @JsonProperty("extraMessage") String extraMessage,
        @JsonProperty("message") String message,
        @JsonProperty("transactionId") String transactionId
    ) {}
    
    // data 부분
    public record Data(
        @JsonProperty("successList") List<Success> successList,
        @JsonProperty("errorList") List<Error> errorList,
        @JsonProperty("connectedId") String connectedId
    ) {}
    
    // successList 안에 들어가는 객체
    public record Success(
        @JsonProperty("clientType") String clientType,
        @JsonProperty("code") String code,
        @JsonProperty("loginType") String loginType,
        @JsonProperty("countryCode") String countryCode,
        @JsonProperty("organization") String organization,
        @JsonProperty("extraMessage") String extraMessage,
        @JsonProperty("businessType") String businessType,
        @JsonProperty("message") String message,
        @JsonProperty("transactionId") String transactionId
    ) {}
    
    // errorList 안에 들어가는 객체 (현재 비어있지만 확장성 고려)
    public record Error(
        @JsonProperty("code") String code,
        @JsonProperty("extraMessage") String extraMessage,
        @JsonProperty("message") String message,
        @JsonProperty("transactionId") String transactionId
    ) {}
}
