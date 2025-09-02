package com.example.piggybank.global.codef.service;

import com.example.piggybank.global.codef.dto.CodefConnectedIdReqDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CodefServiceImpl implements CodefService {
    
    @Value("${account.api.public-key}")
    private String PUBLIC_KEY;
    
    @Value("${account.api.client-id}")
    private String CLIENT_ID;
    
    @Value("${account.api.secret}")
    private String CLIENT_SECRET;
    
    private final ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public String publishCodefAccessToken() {
        String basicAuthValue = "Basic " + java.util.Base64.getEncoder().encodeToString(
            (CLIENT_ID + ":" + CLIENT_SECRET).getBytes(StandardCharsets.UTF_8)
        );
        
        log.info("Auth value : " + basicAuthValue);
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "client_credentials");
        params.add("client_id", CLIENT_ID);
        params.add("client_secret", CLIENT_SECRET);
        params.add("scope", "read");
        
        log.info("params : " + params);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Authorization", basicAuthValue);
        
        log.info("headers : " + headers);
        
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
        
        RestTemplate rt = new RestTemplate();
        
        ResponseEntity<String> response = rt.exchange(
            "https://oauth.codef.io/oauth/token", //{요청할 서버 주소}
            HttpMethod.POST, //{요청할 방식}
            entity, // {요청할 때 보낼 데이터}
            String.class //{요청시 반환되는 데이터 타입}
        );
        
        log.info("response : " + response);
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(response.getBody());
            String accessToken = jsonNode.get("access_token").asText();
            log.info("access_token : " + accessToken);
            return accessToken;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse access_token", e);
        }
    }
    
    
    @Override
    public String publishCodefConnectedId(CodefConnectedIdReqDto reqDto) {
        
        String bearerAuthValue = "Bearer " + reqDto.accessToken();
        
        String encodedPassword = Base64.getEncoder().encodeToString(reqDto.bankPassword().getBytes(StandardCharsets.UTF_8));
        
        log.info("encodedPassword : " + encodedPassword);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Authorization", bearerAuthValue);
        
        Map<String, Object> account = new HashMap<>();
        account.put("countryCode", "KR");
        account.put("businessType", "BK");
        account.put("clientType", "P"); // 개인 : P / 기업 : B / 통합 : A
        account.put("organization", "0004"); // 0004 = KB 국민은행
        account.put("loginType", "1"); // 0: 인증서, 1: 아이디/패스워드
        account.put("id", reqDto.bankId());
        account.put("password", encodedPassword); // RSA 암호화 값
        
        // accountList 배열에 담기
        Map<String, Object> body = new HashMap<>();
        body.put("accountList", Collections.singletonList(account));
        
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        
        RestTemplate rt = new RestTemplate();
        
        ResponseEntity<String> response = rt.exchange(
            "https://development.codef.io/v1/account/create", //{요청할 서버 주소}
            HttpMethod.POST, //{요청할 방식}
            entity,
            String.class //{요청시 반환되는 데이터 타입}
        );
        
        return response.getBody();
        
    }
    
    
    
    public String encryptPassword(String password) throws Exception {
        // Base64로 디코딩
        byte[] keyBytes = Base64.getDecoder().decode(PUBLIC_KEY);
        
        // 공개키 객체 생성
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(spec);
        
        // Cipher 객체 생성
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        
        // 암호화
        byte[] encryptedBytes = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
        
        // 최종적으로 Base64 인코딩된 문자열 반환
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}
