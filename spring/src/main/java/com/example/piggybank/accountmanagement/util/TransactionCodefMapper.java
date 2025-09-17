package com.example.piggybank.accountmanagement.util; // // DTO와 같은 패키지에 두면 편함

import com.example.piggybank.accountmanagement.api.dto.response.FromCodefResponse;
import com.example.piggybank.global.codef.dto.res.CodefTransactionResDto.TranHistory;
import java.math.BigDecimal;                 // // 금액 문자열 파싱용
import java.time.LocalDate;                  // // 날짜 파싱용
import java.time.LocalDateTime;              // // 최종 일시
import java.time.LocalTime;                  // // 시간 파싱용
import java.time.format.DateTimeFormatter;   // // 포맷터
import java.util.ArrayList;                  // // 리스트 생성
import java.util.List;                       // // 리스트 반환

/**
 * Codef TranHistory -> FromCodefTransaction 변환 유틸
 */
public final class TransactionCodefMapper {
    
    private TransactionCodefMapper() {}  // // 유틸 클래스이므로 인스턴스화 방지
    
    // // Codef 포맷(yyyyMMdd / HHmmss)에 맞춘 포맷터
    private static final DateTimeFormatter D_FMT = DateTimeFormatter.ofPattern("yyyyMMdd"); // // 날짜 포맷
    private static final DateTimeFormatter T_FMT = DateTimeFormatter.ofPattern("HHmmss");   // // 시간 포맷
    
    /**
     * TranHistory 1건 -> FromCodefTransaction 1건
     */
    public static FromCodefResponse from(TranHistory th) {
        // // 1) 거래 일시 합성: yyyyMMdd + HHmmss → LocalDateTime
        LocalDateTime ldt = LocalDateTime.of(                 // // LocalDate와 LocalTime을 합쳐서 LocalDateTime 생성
            LocalDate.parse(safe(th.resAccountTrDate()), D_FMT), // // yyyyMMdd → LocalDate
            LocalTime.parse(safe(th.resAccountTrTime()), T_FMT)  // // HHmmss   → LocalTime
        );
        
        // // 2) 금액 문자열 숫자화 (입금/출금 중 값 있는 쪽 사용)
        long in  = toLong(th.resAccountIn());                 // // 입금 금액 문자열 → long
        long out = toLong(th.resAccountOut());                // // 출금 금액 문자열 → long
        
        // // 3) 방향 및 금액 결정
        final boolean isWithdrawal = (out > 0);              // // 출금 금액이 존재하면 출금(true)
        long amount = isWithdrawal ? out : in;               // // 있는 쪽 금액 사용(양수)
        
        // // 4) 설명: resAccountDesc3 사용 (원문 요구사항)
        String desc = safe(th.resAccountDesc3());            // // null 방지용 safe 처리
        
        // // 5) DTO 생성해서 반환
        return new FromCodefResponse(                     // // 정규화된 DTO 생성
            ldt,                                         // // 거래일시
            amount,                                      // // 금액 (양수)
            isWithdrawal,                                // // 출금=true(=1), 입금=false(=0)
            desc                                         // // 설명(resAccountDesc3)
        );
    }
    
    /**
     * TranHistory 리스트 -> FromCodefTransaction 리스트
     */
    public static List<FromCodefResponse> fromList(List<TranHistory> list) {
        // // 널/빈 처리
        if (list == null || list.isEmpty()) return List.of(); // // null 또는 빈 리스트면 불변 빈 리스트 반환
        var result = new ArrayList<FromCodefResponse>(list.size()); // // 용량 최적화하여 ArrayList 생성
        for (TranHistory th : list) {                         // // 각 항목 순회
            result.add(from(th));                             // // 단건 매핑 재사용
        }
        return result;                                        // // 결과 리스트 반환
    }
    
    // ===== 내부 헬퍼 =====
    
    private static String safe(String s) {
        return (s == null) ? "" : s;                          // // null이면 빈 문자열로 변환
    }
    
    private static long toLong(String num) {
        // // ① null/공백 방어
        String v = safe(num).trim();                          // // 양끝 공백 제거
        if (v.isEmpty()) return 0L;                           // // 빈 값은 0 처리
        
        // // ② 쉼표/원화기호 등 비숫자 제거 (소수점도 제거해서 원 단위 Long로)
        v = v.replaceAll("[^0-9.-]", "");                     // // 숫자/부호/점 외 제거
        if (v.isEmpty() || v.equals("-") || v.equals("."))    // // 의미 없는 경우 방어
            return 0L;                                        // // 0 처리
        
        // // ③ BigDecimal로 안전 파싱 후 '원' 단위 Long로 변환(소수점 버림/반올림 정책은 필요에 맞게 수정)
        try {
            BigDecimal bd = new BigDecimal(v);                // // 문자열 → BigDecimal
            bd = bd.setScale(0, java.math.RoundingMode.HALF_UP); // // 소수점 반올림해서 정수 원으로
            long val = bd.longValueExact();                   // // long 추출 (범위 넘치면 예외)
            if (val < 0) return Math.abs(val);                // // 혹시 음수면 절대값으로 통일
            return val;                                       // // 양수 반환
        } catch (Exception e) {
            return 0L;                                        // // 파싱 실패 시 0
        }
    }
}
