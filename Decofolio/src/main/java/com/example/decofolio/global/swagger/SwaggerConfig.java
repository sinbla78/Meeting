package com.example.decofolio.global.swagger;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        // Bearer Token 인증 방식 설정
        SecurityScheme apiKey = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP) // HTTP 방식
                .scheme("bearer") // bearer 방식 사용
                .bearerFormat("JWT") // JWT 형식 사용
                .name("Authorization"); // 헤더 이름 설정

        // 보안 요구 사항에 Bearer Token 추가
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Bearer Token");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Bearer Token", apiKey)) // Bearer Token 인증 방식 설정
                .addSecurityItem(securityRequirement) // 보안 요구 사항 추가
                .info(apiInfo()); // API 기본 정보 설정
    }

    // API에 대한 기본 정보 제공
    private Info apiInfo() {
        return new Info()
                .title("API Test") // API 제목
                .description("Let's practice Swagger UI") // API 설명
                .version("1.0.0"); // API 버전
    }

    // Jackson 설정: 카멜케이스로 필드명 자동 변환
    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .propertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE) // 카멜케이스 설정
                .build();
    }
}

