package com.example.piggybank.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${python.base-url}")
    private String pythonBaseUrl;

    @Bean
    public WebClient pythonClient() {
        return WebClient.builder()
            .baseUrl(pythonBaseUrl)
            .build();
    }

}
