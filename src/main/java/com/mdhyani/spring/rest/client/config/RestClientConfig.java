package com.mdhyani.spring.rest.client.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient () {
        return RestClient.
                builder().baseUrl("http://localhost:8082/user-service").build();
    }

   /* @Bean
    public CommandLineRunner commandLineRunner() {
        return (args) -> {
            System.out.println("args" +args);
        };
    }*/
}
