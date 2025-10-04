package com.example.piggybank.global.codef.service;

import com.example.piggybank.global.codef.dto.req.CodefConnectedIdReqDto;
import com.example.piggybank.global.codef.dto.res.CodefConnectedIdResDto;
import com.example.piggybank.global.codef.dto.req.CodefTransactionReqDto;
import com.example.piggybank.global.codef.dto.res.CodefTransactionResDto;
import com.example.piggybank.global.codef.dto.res.CodefTransactionResDto.TranHistory;
import com.example.piggybank.global.codef.enums.BankType;
import com.example.piggybank.global.codef.event.CodefIdCreatedEvent;
import com.example.piggybank.global.codef.event.CodefTranHistoryCreatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
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
    
    private final ObjectMapper objectMapper;
    @Value("${account.api.public-key}")
    private String PUBLIC_KEY;
    
    @Value("${account.api.client-id}")
    private String CLIENT_ID;
    
    @Value("${account.api.secret}")
    private String CLIENT_SECRET;
    
    @Value("${codef.api.access-token-url}")
    private String ACCESS_TOKEN_URL;
    
    @Value("${codef.api.connected-id-url}")
    private String CONNECTED_ID_URL;
    
    @Value("${codef.api.transaction-request-url}")
    private String TRANSACTION_REQUEST_URL;
    
    
    private final ObjectMapper mapper = new ObjectMapper();
    private final ApplicationEventPublisher eventPublisher;
    
    @Override
    public String publishCodefAccessToken() {
        String basicAuthValue = "Basic " + Base64.getEncoder().encodeToString(
            (CLIENT_ID + ":" + CLIENT_SECRET).getBytes(StandardCharsets.UTF_8)
        );
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "client_credentials");
        params.add("client_id", CLIENT_ID);
        params.add("client_secret", CLIENT_SECRET);
        params.add("scope", "read");
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Authorization", basicAuthValue);
        
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
        
        RestTemplate rt = new RestTemplate();
        
        ResponseEntity<String> response = rt.exchange(
            ACCESS_TOKEN_URL,
            HttpMethod.POST,
            entity,
            String.class
        );
        
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
        
        String organizationCode = BankType.getCodeByName(reqDto.account().getBankName());
        
        String encodedPassword = null;
        
        try {
            encodedPassword = encryptPassword(reqDto.bankPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", bearerAuthValue);
        
        Map<String, Object> account = new HashMap<>();
        account.put("countryCode", "KR");
        account.put("businessType", "BK");
        account.put("clientType", "P"); // 개인 : P / 기업 : B / 통합 : A
        account.put("organization", organizationCode);
        account.put("loginType", "1"); // 0: 인증서, 1: 아이디/패스워드
        account.put("id", reqDto.bankId());
        account.put("password", encodedPassword); // RSA 암호화 값
        
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.put("accountList", Collections.singletonList(account));
        
        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
        
        RestTemplate rt = new RestTemplate();
        
        ResponseEntity<String> response = rt.exchange(
            CONNECTED_ID_URL,
            HttpMethod.POST,
            entity,
            String.class
        );
        String decodedResponse = URLDecoder.decode(response.getBody(), StandardCharsets.UTF_8);
        
        CodefConnectedIdResDto jsonConnectedId = connectedIdToJson(decodedResponse);
        String connectedId = jsonConnectedId.data().connectedId();
        
        log.info("connectedId : " + connectedId);
        
        eventPublisher.publishEvent(new CodefIdCreatedEvent(this, connectedId, reqDto.account()));
        
        return connectedId;
    }
    
    @Override
    public List<TranHistory> getCodefTransactions(CodefTransactionReqDto reqDto) {
        
        String bearerAuthValue = "Bearer " + reqDto.accessToken();
        
        String organizationCode = BankType.getCodeByName(reqDto.organization());
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", bearerAuthValue);
        
        log.info("headers : " + headers);
        
        Map<String, Object> body = new HashMap<>();
        body.put("connectedId", reqDto.connectedId());
        body.put("organization", organizationCode);
        body.put("account", reqDto.accountNumber());
        body.put("startDate", reqDto.startDate());
        body.put("endDate", reqDto.endDate());
        body.put("orderBy", "0");
        body.put("inquiryType","1");
        body.put("clientType","P");
        
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        
        RestTemplate rt = new RestTemplate();
        
        ResponseEntity<String> response = rt.exchange(
            TRANSACTION_REQUEST_URL,
            HttpMethod.POST,
            entity,
            String.class
        );
        String decodedResponse = URLDecoder.decode(response.getBody(), StandardCharsets.UTF_8);
        
        log.info("decodedResponse : " + decodedResponse);
        
        CodefTransactionResDto codefTransactionResDto = transactionToJson(decodedResponse);
        List<TranHistory> tranHistoryList = codefTransactionResDto.getTranHistories();
        String balance = codefTransactionResDto.getBalance();
        log.info("tranHistoryList : " + tranHistoryList);
        
        eventPublisher.publishEvent(new CodefTranHistoryCreatedEvent(this, tranHistoryList, reqDto.accountId(), balance));
        
        return tranHistoryList;
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
    
    public CodefConnectedIdResDto connectedIdToJson(String decodedUrlConnectedId){
        ObjectMapper mapper = new ObjectMapper();
        
        CodefConnectedIdResDto response = null;
        
        try{
            response = objectMapper.readValue(decodedUrlConnectedId, CodefConnectedIdResDto.class);
        } catch(JsonProcessingException e){
            throw new RuntimeException("Connected Id Json 파싱 중 오류 발생");
        }
        
        return response;
    }
    
    
    
    public CodefTransactionResDto transactionToJson(String decodedUrlTransaction){
        
        ObjectMapper objectMapper = new ObjectMapper();
        
        CodefTransactionResDto response = null;
        try {
            response = objectMapper.readValue(decodedUrlTransaction, CodefTransactionResDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("거래내역 Json 파싱 중 오류 발생");
        }
        
        return response;
    }
}
