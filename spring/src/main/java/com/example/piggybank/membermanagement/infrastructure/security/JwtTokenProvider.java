package com.refactoring.piggybank.membermanagement.infrastructure.security;

// username 가져오기, authentication 가져오기,
// 해당 토큰이 사용가능한 토큰인지, 토큰 가져오기(requestHeader),
// 토큰 만료여부, 토큰 확인

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.token-validity-in-milliseconds}")
    private long tokenValidityInMilliSeconds;

    private Key key;
    private JwtParser jwtParser;

    @PostConstruct
    protected void init() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.jwtParser = Jwts.parserBuilder()
            .setSigningKey(key)
            .build();
    }

    // token 생성
    public String createToken(UUID userId, String email) {
        Claims claims = Jwts.claims().setSubject(userId.toString());
        String role = email.equals("admin@test.com") ? "ROLE_ADMIN" : "ROLE_USER";
        claims.put("role", role);

        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidityInMilliSeconds);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    // token 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // token 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jws<Claims> jws = jwtParser.parseClaimsJws(token);
            return jws.getBody().getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserId(String token) {
        return jwtParser.parseClaimsJws(token).getBody().getSubject();
    }

    public String getRole(String token) {
        Object raw = jwtParser.parseClaimsJws(token).getBody().get("role");
        return raw == null ? null : raw.toString();
    }

}
