package com.example.piggybank.accountmanagement.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record FromCodefResponse(
    LocalDateTime transactionDate, // // 거래일시(분/초까지)
    Long          amount,          // // 금액(Long, 양수)
    boolean       inoutType,       // // 출금이면 true(=1), 입금이면 false(=0)
    String        description      // // resAccountDesc3 값
) {}