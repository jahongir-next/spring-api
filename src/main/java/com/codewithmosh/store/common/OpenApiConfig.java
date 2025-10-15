package com.codewithmosh.store.common;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Store API")
                        .version("1.0.0")
                        .description("API documentation for the Grocery Store project"))
                .servers(List.of(
                        new Server().url("https://spring-api-production-e308.up.railway.app") // ✅ must match your deployed HTTPS URL
                ));
    }
}
