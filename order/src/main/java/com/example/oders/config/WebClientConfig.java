package com.example.oders.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    public WebClient inventoryWebClient() {
        return WebClient.builder().baseUrl("http://localhost:8081/api/v1").build();
    }

    @Bean
    public  WebClient productWebClient() {
        return WebClient.builder().baseUrl("http://localhost:8083/api/v1").build();
    }
}
