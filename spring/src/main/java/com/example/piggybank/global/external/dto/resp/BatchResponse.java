package com.refactoring.piggybank.global.external.dto.resp;

import java.util.List;

// Spring 분석 결과 묶음
public record BatchResponse(
    List<BatchResponseItem> items
) {

}
