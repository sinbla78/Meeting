package com.example.decofolio.domain.auth.controller.dto;

import com.example.decofolio.domain.auth.controller.dto.request.SignInRequest;
import com.example.decofolio.domain.auth.controller.dto.response.TokenResponse;
import com.example.decofolio.domain.auth.service.SignInService;
import com.example.decofolio.domain.auth.service.TokenService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@RestController
public class AuthController {

    private final TokenService tokenService;
    private final SignInService signInService;

    /**
     * 토큰 재발급 처리
     * @param refreshToken Refresh Token
     * @return 새로 발급된 Access Token
     */
    @ApiOperation(value = "토큰 재발급")
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
    @ApiOperation(value = "로그인")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public TokenResponse signIn(@RequestBody @Valid SignInRequest signInRequest) {
        return signInService.execute(signInRequest);
    }
}

