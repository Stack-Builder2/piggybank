package com.example.piggybank.global.codef.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.List;

// 거래내역 응답 최상위 DTO
@JsonIgnoreProperties(ignoreUnknown = true) // // 모르는 필드는 무시 (응답 확장 대비)
public record CodefTransactionResDto(
    
    @JsonProperty("result")         // // 처리 결과 블록
    Result result,
    
    @JsonProperty("clientType")     // // P/B/A
    String clientType,
    
    @JsonProperty("data")           // // 실제 데이터 블록
    Data data,
    
    @JsonProperty("connectedId")    // // 루트에 있는 connectedId
    String connectedId
) {
    // result 블록
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Result(
        @JsonProperty("code") String code,                 // 결과 코드 (CF-00000 등)
        @JsonProperty("extraMessage") String extraMessage, // 추가 메시지
        @JsonProperty("message") String message,           // 메시지
        @JsonProperty("transactionId") String transactionId// 트랜잭션 ID
    ) {}
    
    // data 블록
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Data(
        @JsonProperty("resAccountBalance") String resAccountBalance,   // 잔액 (문자열로 옴)
        @JsonProperty("resWithdrawalAmt") String resWithdrawalAmt,     // 출금액 합계
        @JsonProperty("resAccountDisplay") String resAccountDisplay,   // 마스킹 계좌번호
        @JsonProperty("resAccount") String resAccount,                 //  원본 계좌번호
        @JsonProperty("resAccountName") String resAccountName,         //  계좌명
        @JsonProperty("resAccountNickName") String resAccountNickName, //  별칭
        @JsonProperty("resAccountHolder") String resAccountHolder,     //  예금주
        @JsonProperty("resAccountStartDate") String resAccountStartDate,// 개설일
        @JsonProperty("resManagementBranch") String resManagementBranch,//  영업점
        @JsonProperty("resAccountStatus") String resAccountStatus,     //  상태
        @JsonProperty("resLastTranDate") String resLastTranDate,       //  최종 거래일
        @JsonProperty("resLoanEndDate") String resLoanEndDate,         //  대출만기
        @JsonProperty("resLoanLimitAmt") String resLoanLimitAmt,       //  한도
        @JsonProperty("resInterestRate") String resInterestRate,       //  금리
        @JsonProperty("commStartDate") String commStartDate,           //  조회 시작일
        @JsonProperty("commEndDate") String commEndDate,               //  조회 종료일
        @JsonProperty("resTrHistoryList") List<TranHistory> resTrHistoryList //  거래내역 리스트
    ) {}
    
    // 거래내역 아이템
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record TranHistory(
        @JsonProperty("resAccountTrDate") String resAccountTrDate, //  거래일(yyyyMMdd) ****
        @JsonProperty("resAccountTrTime") String resAccountTrTime, //  거래시각(HHmmss) **** 이걸로 구분
        @JsonProperty("resAccountOut") String resAccountOut,       //  출금 금액
        @JsonProperty("resAccountIn") String resAccountIn,         //  입금 금액
        @JsonProperty("resAccountDesc1") String resAccountDesc1,   //  설명1
        @JsonProperty("resAccountDesc2") String resAccountDesc2,   //  설명2
        @JsonProperty("resAccountDesc3") String resAccountDesc3,   //  설명3(가맹점명 등)
        @JsonProperty("resAccountDesc4") String resAccountDesc4,   //  설명4(카드사 등)
        @JsonProperty("resAfterTranBalance") String resAfterTranBalance, //  거래 후 잔액
        @JsonProperty("tranDesc") String tranDesc                  //  비고
    ) {}
    public List<TranHistory> getTranHistories() {
        if (data != null && data.resTrHistoryList() != null) {
            return data.resTrHistoryList();
        }
        return Collections.emptyList();
    }
}
