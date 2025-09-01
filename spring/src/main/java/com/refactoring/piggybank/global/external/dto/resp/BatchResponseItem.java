package com.refactoring.piggybank.global.external.dto.resp;

// Spring 분석 결과 항목
public record BatchResponseItem(
    String id,
    String description,
    String category
) {

}
