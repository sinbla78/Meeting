package com.example.decofolio.global.swagger;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import java.util.Collections;


import io.swagger.v3.oas.models.parameters.HeaderParameter;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("new_year API")
                        .description("모임 기반 커뮤니티 서비스 new_year의 API입니다.")
                        .version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList("Authorization"))
                .components(new Components()
                        .addSecuritySchemes("Authorization",
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.HTTP)
                                        .in(SecurityScheme.In.HEADER)
                                        .description("JWT 토큰 입력 (Bearer 토큰)")
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))
                        .addParameters("test-token",
                                new HeaderParameter()
                                        .name("Authorization")
                                        .description("테스트용 토큰")
                                        .example("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")));
    }
}