package org.furia.chatbot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI () {

        return new OpenAPI()
                .info(
                        new Info().title("Technical_Challenge_FURIA")
                        .version("1.0")
                        .description("API de chatbot criada para o desafio técnico da FURIA")
                        .license(new License().name("MIT License").url("https://opensource.org/licenses/MIT"))
                        .contact(new Contact().name("Invked").email("samuelnobrega902@gmail.com").url("https://github.com/Invokedzz"))
                );

    }

}
