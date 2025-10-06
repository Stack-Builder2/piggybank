package com.example.piggybank.global.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

  INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
  INVALID_TYPE_VALUE(400, "C002", "Invalid Type Value"),
  ACCESS_DENIED(403, "C003", "Access is Denied"),
  RESOURCE_NOT_FOUND(404, "C004", "Resource Not Found"),
  METHOD_NOT_ALLOWED(405, "C005", "Method Not Allowed"),
  INTERNAL_SERVER_ERROR(500, "C006", "Server Error"),
  GATEWAY_TIMEOUT(501, "C007", "Gateway Timeout"),

  EMAIL_DUPLICATION(400, "U001", "Email is Duplicated"),
  USER_NOT_FOUND(404, "U002", "User Not Found"),
  PASSWORD_NOT_MATCH(400, "U003", "Password Not Match"),
  
  PROFILE_DUPLICATION(400, "P001", "Profile Duplication"),

  CATEGORY_NOT_FOUND(404, "CT001", "Category Not Found"),

  // 추가
  ACCOUNT_NOT_FOUND(404, "A001", "Account Not Found"),
  TRANSACTION_NOT_FOUND(404, "T002", "Transaction Not Found"),
  PROFILE_NOT_FOUND(404, "P001", "Profile Not Found"),

  INVALID_TOKEN(400, "T001", "Invalid Token Value"),

  INVALID_ACCESS_TOKEN(400, "AC001", "Failed to parse access_token"),
  TRANSACTION_JSON_PARSING_FAILED(400, "AC002", "거래내역 Json 파싱 중 오류 발생"),
  CONNECTED_ID_JSON_PARSING_FAILED(400, "AC003", "Connected Id Json 파싱 중 오류 발생"),
  PASSWORD_ENCRYPTION_FAILED(500, "AC005", "비밀번호 암호화 중 오류 발생"),

  EMAIL_SEND_FAILED(400, "E001", "HTML 이메일 발송 중 오류 발생"),

  BALANCE_NOT_FOUND(400, "B001", "Balance cannot be null or empty");

  private final int status;
  private final String code;
  private final String message;

  ErrorCode(int status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

}