package com.example.decofolio.global.security.config;

import com.example.decofolio.global.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .cors()

                .and()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()


                //user
                .antMatchers(HttpMethod.POST, "/v1/user").permitAll()
                .antMatchers(HttpMethod.PATCH, "/v1/user/password").authenticated()
                .antMatchers(HttpMethod.PATCH, "/v1/user/{userId}/state").authenticated()
                .antMatchers(HttpMethod.GET, "/v1/user/{userId}").authenticated()
                //auth
                .antMatchers(HttpMethod.POST, "/v1/auth/login").permitAll()
                .antMatchers(HttpMethod.PATCH, "/v1/auth/token").permitAll()
                //meeting
                .antMatchers(HttpMethod.POST, "/v1/meeting").authenticated()
                .antMatchers(HttpMethod.GET, "/v1/meeting/{meetingId}").authenticated()
                .antMatchers(HttpMethod.POST, "/v1/meeting/{meetingId}/join").authenticated()
                .antMatchers(HttpMethod.GET, "/v1/meeting/search").authenticated()

                //comment
                .antMatchers(HttpMethod.GET, "/v1/comments/{meetingId}").authenticated()
                .antMatchers(HttpMethod.POST, "/v1/comments/{meetingId}").authenticated()
                //swagger
                .antMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/webjars/**").permitAll()

                .antMatchers("/actuator/health", "/actuator/info").permitAll()

                .anyRequest().denyAll()

                .and()
                .apply(new FilterConfig(jwtTokenProvider, objectMapper));
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
