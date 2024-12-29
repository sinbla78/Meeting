package com.example.decofolio.global.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        String jwt = "JWT";

        // 보안 요구 사항 설정 (JWT 사용)
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt);

        // JWT 인증 방식에 대한 보안 구성 추가
        Components components = new Components().addSecuritySchemes(jwt, new SecurityScheme()
                .name(jwt)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
        );

        // OpenAPI 설정 반환
        return new OpenAPI()
                .components(components)
                .info(apiInfo()) // API 정보 설정
                .addSecurityItem(securityRequirement); // 보안 요구 사항 추가
    }

    private Info apiInfo() {
        return new Info()
                .title("API Test") // API 제목
                .description("Let's practice Swagger UI") // API 설명
                .version("1.0.0"); // API 버전
    }
}
