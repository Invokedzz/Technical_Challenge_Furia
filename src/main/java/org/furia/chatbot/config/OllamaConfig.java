package org.furia.chatbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaConfig {

    @Value("${ai.prompt}")
    private String prompt;

}
