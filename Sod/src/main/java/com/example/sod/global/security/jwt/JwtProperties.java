package com.example.sod.global.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.jwt")
public class JwtProperties {

    private final String secret;
    private final Long accessExp;
    private final Long refreshExp;
    private final String header;
    private final String prefix;
}