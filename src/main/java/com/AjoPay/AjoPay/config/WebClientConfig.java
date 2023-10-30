package com.AjoPay.AjoPay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    // I am trying to use webclient library
    @Bean
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();

    }

}
