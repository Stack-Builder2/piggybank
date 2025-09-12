package com.refactoring.piggybank.global.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtTokenProvider jwtTokenProvider;
    
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String p = request.getServletPath();
        return p.startsWith("/api/v1/email")
            || p.startsWith("/api/v1/sms")
            || p.startsWith("/swagger-ui")
            || p.startsWith("/v3/api-docs")
            || p.startsWith("/swagger-resources")
            || p.startsWith("/webjars");
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        
        String token = jwtTokenProvider.resolveToken(request);
        
        try {
            if (!org.springframework.util.StringUtils.hasText(token) ||
                SecurityContextHolder.getContext().getAuthentication() != null) {
                filterChain.doFilter(request, response);
                return;
            }
            
            if (jwtTokenProvider.validateToken(token)) {
                String userId = jwtTokenProvider.getUserId(token);
                String role = jwtTokenProvider.getRole(token);
                
                var authorities = (role == null || role.isBlank())
                    ? List.<SimpleGrantedAuthority>of()
                    : List.of(new SimpleGrantedAuthority(role));
                
                var auth = new UsernamePasswordAuthenticationToken(userId, token, authorities);
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                var context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(auth);
                SecurityContextHolder.setContext(context);
            }
            
            filterChain.doFilter(request, response);
            
        } catch (io.jsonwebtoken.JwtException |
                 org.springframework.security.core.AuthenticationException |
                 IllegalArgumentException ex) {
            SecurityContextHolder.clearContext();
            
            filterChain.doFilter(request, response);
            
        }
    }
}