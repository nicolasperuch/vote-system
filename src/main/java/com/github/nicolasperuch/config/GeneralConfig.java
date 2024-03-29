package com.github.nicolasperuch.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GeneralConfig {

    @Bean
    public Gson gson(){
        return new Gson();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
