package com.example.decofolio.global.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("new_year API")
                        .description("모임 기반 커뮤니티 서비스 new_year의 API입니다.")
                        .version("1.0.0"))
                // 보안 요구 사항 추가
                .addSecurityItem(new SecurityRequirement().addList("Authorization"))
                // Security Scheme 정의 및 기본값 설정
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Authorization", new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .description("JWT 토큰 입력 (Bearer 토큰)")
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
