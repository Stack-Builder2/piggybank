package com.example.piggybank.global.error.exception;

import com.example.piggybank.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException {

  public EntityNotFoundException(String message){
    super(message, ErrorCode.RESOURCE_NOT_FOUND);
  }

  public EntityNotFoundException(ErrorCode errorCode){
    super(ErrorCode.RESOURCE_NOT_FOUND);
  }


}
