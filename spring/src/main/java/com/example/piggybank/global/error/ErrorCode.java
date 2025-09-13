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

  EMAIL_DUPLICATION(400, "U001", "Email is Duplicated"),
  USER_NOT_FOUND(404, "U002", "User Not Found"),
  PASSWORD_NOT_MATCH(400, "U003", "Password Not Match"),

  CATEGORY_NOT_FOUND(404, "CT001", "Category Not Found"),

  INVALID_TOKEN(400, "T001", "Invalid Token Value");

  private final int status;
  private final String code;
  private final String message;

  ErrorCode(int status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

}
