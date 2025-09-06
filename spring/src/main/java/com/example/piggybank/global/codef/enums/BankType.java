package com.example.piggybank.global.codef.enums;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BankType {
    KDB("산업은행", "0002"),
    IBK("기업은행", "0003"),
    KB("국민은행", "0004"),
    SH("수협은행", "0007"),
    NH("농협은행", "0011"),
    WOORI("우리은행", "0020"),
    SC("SC은행", "0023"),
    CITY("시티은행", "0027"),
    IM("대구은행", "0031"),
    BNK("부산은행", "0032"),
    PIB("광주은행", "0034"),
    JEJU("제주은행", "0035"),
    JB("전북은행", "0037"),
    KN("경남은행", "0039"),
    MG("새마을금고", "0045"),
    OPEN("신협은행", "0048"),
    EPOST("우체국은행", "0071"),
    EPOST2("우체국", "0071"),
    KEB("하나은행", "0081"),
    KEB2("KEB", "0081"),
    KEB3("KEB하나은행", "0081"),
    SHINHAN("신한은행", "0088"),
    K("K뱅크", "0089");
    
    private final String name; // 한국어 은행명
    private final String code; // 기관 코드
    
    public static String getCodeByName(String name) {
        return Arrays.stream(BankType.values())
            .filter(bank -> bank.getName().equals(name))
            .findFirst()
            .map(BankType::getCode)
            .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 은행명: " + name));
    }
}
