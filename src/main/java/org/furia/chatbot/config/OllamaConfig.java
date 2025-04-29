package org.furia.chatbot.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaConfig {

    @Value("${ai.prompt}")
    private String prompt;

    private final ChatClient chatClient;

    public OllamaConfig (ChatClient.Builder chatClient) {

        this.chatClient = chatClient.build();

    }

    public String getAIResponse (String message) {

        return chatClient
                .prompt(prompt)
                .user(message)
                .call()
                .content();

    }

}
