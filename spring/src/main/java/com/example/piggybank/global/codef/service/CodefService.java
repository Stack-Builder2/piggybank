package com.example.piggybank.global.codef.service;

import com.example.piggybank.global.codef.dto.CodefConnectedIdReqDto;
import org.springframework.http.ResponseEntity;

public interface CodefService {
    String publishCodefAccessToken();
    String publishCodefConnectedId(CodefConnectedIdReqDto codefConnectedIdReqDto);
}
