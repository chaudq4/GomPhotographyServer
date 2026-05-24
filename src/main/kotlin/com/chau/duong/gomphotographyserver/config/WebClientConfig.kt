package com.chau.duong.gomphotographyserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    fun googleDriveWebClient(): WebClient {
        return WebClient.builder()
            .baseUrl("https://www.googleapis.com")
            .build()
    }
}