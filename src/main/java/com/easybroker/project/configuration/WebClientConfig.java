package com.easybroker.project.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${app.easy.url}")
    private String url;

    @Value("${app.easy.key}")
    private String key;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeader("X-Authorization",key)
                .build();
    }
}
