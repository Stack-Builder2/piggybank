package com.example.piggybank.global.codef.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
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
    private String publicKey;
    
    @Value("${account.api.client-id}")
    private String clientId;
    
    @Value("${account.api.secret}")
    private String clientSecret;
    
    private final ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public String publishCodefAccessToken() {
        BufferedReader br = null;
        
        try {
            URL url = new URL("https://oauth.codef.io/oauth/token");
            
            String params = "grant_type=client_credentials"
                + "&client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&scope=read";
            
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            String auth = clientId + ":" + clientSecret;
            byte[] authEncBytes = Base64.encodeBase64(auth.getBytes());
            String authStringEnc = new String(authEncBytes);
            String authHeader = "Basic " + authStringEnc;
            
            con.setRequestProperty("Authorization", authHeader);
            con.setDoInput(true);
            con.setDoOutput(true);
            
            try (OutputStream os = con.getOutputStream()) {
                os.write(params.getBytes());
                os.flush();
            }
            
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            
            StringBuilder responseStr = new StringBuilder();
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                responseStr.append(inputLine);
            }
            
            Map<String, Object> tokenMap = mapper.readValue(
                URLDecoder.decode(responseStr.toString(), "UTF-8"),
                new TypeReference<Map<String, Object>>() {}
            );
            
            return "Bearer " + tokenMap.get("access_token").toString();
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
    }
    
    
    @Override
    public String publishCodefConnectedId(String bank, String bankId, String bankPassword) {
        
        String basicAuthValue = "Basic " + java.util.Base64.getEncoder().encodeToString(
            (clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8)
        );
        
        log.info("Auth value : " + basicAuthValue);
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "client_credentials");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
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
        
        return response.toString();
    }
}
