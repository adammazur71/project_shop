package org.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

// TODO: JavaDoc
@Configuration
@EnableScheduling
public class AppConfig {

    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }

}
