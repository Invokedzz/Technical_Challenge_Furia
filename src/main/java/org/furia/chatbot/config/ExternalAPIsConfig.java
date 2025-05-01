package org.furia.chatbot.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalAPIsConfig {

    @Value("${pandascore.apikey}")
    private String pandaScoreAPIKey;

    @Bean
    public RequestInterceptor requestInterceptor() {

        return template -> {

            template.header("accept", "application/json");
            template.header("Authorization", "Bearer " + pandaScoreAPIKey);

        };

    }

}
