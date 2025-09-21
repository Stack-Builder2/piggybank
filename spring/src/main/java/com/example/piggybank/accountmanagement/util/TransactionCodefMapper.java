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

public final class TransactionCodefMapper {
    
    private TransactionCodefMapper() {}
    
    private static final DateTimeFormatter D_FMT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter T_FMT = DateTimeFormatter.ofPattern("HHmmss");
    
    public static FromCodefResponse from(TranHistory th) {
        LocalDateTime ldt = LocalDateTime.of(
            LocalDate.parse(safe(th.resAccountTrDate()), D_FMT),
            LocalTime.parse(safe(th.resAccountTrTime()), T_FMT)
        );
        
        long in  = toLong(th.resAccountIn());
        long out = toLong(th.resAccountOut());
        
        final boolean isWithdrawal = (out > 0);
        long amount = isWithdrawal ? out : in;
        
        String desc = safe(th.resAccountDesc3());
        
        return new FromCodefResponse(
            ldt,
            amount,
            isWithdrawal,
            desc
        );
    }
    
    public static List<FromCodefResponse> fromList(List<TranHistory> list) {
        // // 널/빈 처리
        if (list == null || list.isEmpty()) return List.of();
        var result = new ArrayList<FromCodefResponse>(list.size());
        for (TranHistory th : list) {
            result.add(from(th));
        }
        return result;
    }
    
    
    private static String safe(String s) {
        return (s == null) ? "" : s;
    }
    
    private static long toLong(String num) {
        String v = safe(num).trim();
        if (v.isEmpty()) return 0L;
        
        v = v.replaceAll("[^0-9.-]", "");
        if (v.isEmpty() || v.equals("-") || v.equals("."))
            return 0L;
        
        try {
            BigDecimal bd = new BigDecimal(v);
            bd = bd.setScale(0, java.math.RoundingMode.HALF_UP);
            long val = bd.longValueExact();
            if (val < 0) return Math.abs(val);
            return val;
        } catch (Exception e) {
            return 0L;
        }
    }
}
