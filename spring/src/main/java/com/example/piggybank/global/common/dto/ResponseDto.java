package com.example.piggybank.global.common.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class ResponseDto {
    private String code;
    private String message;
    private Object data;
    protected ResponseDto() {
        this.code = ResponseCode.SUCCESS;
        this.message = ResponseMessage.SUCCESS;
        this.data = null;
    }
    
    // code, message만 설정하는 생성자
    protected ResponseDto(String message) {
        this.message = message;
        this.data = null;
    }
    
    // 성공 응답 - 기본
    public static ResponseEntity<ResponseDto> success(HttpStatus status) {
        ResponseDto body = new ResponseDto();
        return ResponseEntity.status(status).body(body);
    }
    
    // 성공 응답 - data 포함
    public static ResponseEntity<ResponseDto> success(HttpStatus status, Object data) {
        ResponseDto body = new ResponseDto();
        body.data = data;
        return ResponseEntity.status(status).body(body);
    }
    
    // 성공 응답 - list 포함
    public static ResponseEntity<ResponseDto> success(HttpStatus status, List<String> data) {
        ResponseDto body = new ResponseDto();
        body.data = data;
        return ResponseEntity.status(status).body(body);
    }
}
