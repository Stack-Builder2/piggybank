package com.example.piggybank.global.codef.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CodefTransactionResDto(
    @JsonProperty("result") Result result,          // 처리 결과
    @JsonProperty("clientType") String clientType,  // P / B / A
    @JsonProperty("data") Data data,                // 실제 데이터
    @JsonProperty("connectedId") String connectedId // 연결 ID
) {
    public record Result(
        @JsonProperty("code") String code,
        @JsonProperty("extraMessage") String extraMessage,
        @JsonProperty("message") String message,
        @JsonProperty("transactionId") String transactionId
    ) {}
    
    public record Data(
        @JsonProperty("resAccountBalance") String resAccountBalance,
        @JsonProperty("resWithdrawalAmt") String resWithdrawalAmt,
        @JsonProperty("resAccountDisplay") String resAccountDisplay,
        @JsonProperty("resAccount") String resAccount,
        @JsonProperty("resAccountName") String resAccountName,
        @JsonProperty("resAccountNickName") String resAccountNickName,
        @JsonProperty("resAccountHolder") String resAccountHolder,
        @JsonProperty("resAccountStartDate") String resAccountStartDate,
        @JsonProperty("resManagementBranch") String resManagementBranch,
        @JsonProperty("resAccountStatus") String resAccountStatus,
        @JsonProperty("resLastTranDate") String resLastTranDate,
        @JsonProperty("resLoanEndDate") String resLoanEndDate,
        @JsonProperty("resLoanLimitAmt") String resLoanLimitAmt,
        @JsonProperty("resInterestRate") String resInterestRate,
        @JsonProperty("commStartDate") String commStartDate,
        @JsonProperty("commEndDate") String commEndDate,
        
        // 거래내역 여러 개 → List 로 매핑
        @JsonProperty("resTrHistoryList") List<TransactionHistory> resTrHistoryList
    ) {}
    
    // 개별 거래내역
    public record TransactionHistory(
        @JsonProperty("resAccountTrDate") String resAccountTrDate,        // 거래일자
        @JsonProperty("resAccountTrTime") String resAccountTrTime,        // 거래시간
        @JsonProperty("resAccountOut") String resAccountOut,              // 출금액
        @JsonProperty("resAccountIn") String resAccountIn,                // 입금액
        @JsonProperty("resAccountDesc1") String resAccountDesc1,          // 적요1
        @JsonProperty("resAccountDesc2") String resAccountDesc2,          // 적요2
        @JsonProperty("resAccountDesc3") String resAccountDesc3,          // 적요3
        @JsonProperty("resAccountDesc4") String resAccountDesc4,          // 적요4
        @JsonProperty("resAfterTranBalance") String resAfterTranBalance,  // 거래 후 잔액
        @JsonProperty("tranDesc") String tranDesc                         // 추가 설명
    ) {}
}
