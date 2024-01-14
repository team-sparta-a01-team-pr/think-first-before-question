package com.sparta.tfbq.global.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openapi(): OpenAPI = OpenAPI()
        .components(Components())
        .info(
            Info()
                .title("TFBQ API")
                .description("TFBQ API Schema")
                .version("1.0.0")
        )

}