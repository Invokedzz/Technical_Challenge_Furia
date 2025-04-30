package org.furia.chatbot.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaConfig {

    private final ChatClient chatClient;

    public OllamaConfig (ChatClient.Builder chatClient) {

        this.chatClient = chatClient.build();

    }

    public String getAIResponse (String message) {

        return chatClient
                .prompt("Oi")
                .user(message)
                .call()
                .content();

    }

}
