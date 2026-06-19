package com.TiendaT.Tienda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
            //Se conecta al microservicio de catalogo
            .baseUrl("http://localhost:8085")
            .build();
    }
    
}
