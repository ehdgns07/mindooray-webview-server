package com.nhnacademy.springboot.gatewayserver.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {
    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
