package org.furia.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FuriaApplication {

    public static void main (String[] args) {

        SpringApplication.run(FuriaApplication.class, args);

    }

}
