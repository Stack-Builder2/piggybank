package com.example.piggybank.global.codef.service;

import org.springframework.http.ResponseEntity;

public interface CodefService {
    String publishCodefAccessToken();
    String publishCodefConnectedId(String bank, String bankId, String bankPassword);
}
