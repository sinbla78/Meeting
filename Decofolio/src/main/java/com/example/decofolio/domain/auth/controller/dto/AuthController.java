package com.example.decofolio.domain.auth.controller.dto;

import com.example.decofolio.domain.auth.controller.dto.request.SignInRequest;
import com.example.decofolio.domain.auth.controller.dto.response.TokenResponse;
import com.example.decofolio.domain.auth.service.SignInService;
import com.example.decofolio.domain.auth.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@RestController
@Tag(name = "User", description = "회원 관련 api")
public class AuthController {

    private final TokenService tokenService;
    private final SignInService signInService;

    /**
     * 토큰 재발급 처리
     * @param refreshToken Refresh Token
     * @return 새로 발급된 Access Token
     */
    @Operation(summary = "토큰 재발급 처리", description = "refreshToken Refresh Token")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/token")
    public TokenResponse userTokenRefresh(@RequestHeader("Refresh-Token") String refreshToken) {
        return tokenService.execute(refreshToken);
    }

    /**
     * 로그인 처리
     * @param signInRequest 로그인 요청 정보
     * @return Access Token
     */
    @Operation(summary = "로그인 처리", description = "login")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public TokenResponse signIn(@RequestBody @Valid SignInRequest signInRequest) {
        return signInService.execute(signInRequest);
    }
}

