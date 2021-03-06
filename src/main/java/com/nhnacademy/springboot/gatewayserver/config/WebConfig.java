package com.nhnacademy.springboot.gatewayserver.config;

import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.setReadTimeout(Duration.ofSeconds(5L))
            .setConnectTimeout(Duration.ofSeconds(5L))
            .build();
    }
}
