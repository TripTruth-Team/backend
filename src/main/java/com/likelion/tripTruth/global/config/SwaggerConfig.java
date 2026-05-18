package com.likelion.tripTruth.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI tripTruthOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TripTruth API 명세서")
                        .description("TripTruth API입니다.")
                        .version("v1.0.0"));
    }
}