package com.refactoring.piggybank.global.error.exception;

import com.refactoring.piggybank.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException {

  public EntityNotFoundException(String message){
    super(message, ErrorCode.RESOURCE_NOT_FOUND);
  }

  public EntityNotFoundException(ErrorCode errorCode){
    super(ErrorCode.RESOURCE_NOT_FOUND);
  }


}
