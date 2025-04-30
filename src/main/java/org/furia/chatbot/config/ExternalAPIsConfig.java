package org.furia.chatbot.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalAPIsConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {

        return template -> {

            template.header("content-Type", "application/json");
            template.header("accept", "application/json");

        };

    }

}
