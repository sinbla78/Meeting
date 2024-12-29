package com.example.decofolio.domain.auth.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class SignInRequest {

    @NotBlank(message = "account_id는 Null, 공백, 띄어쓰기를 사용하지 않습니다.")
    @JsonProperty("accountId")
    private String accountId;

    @NotBlank(message = "password는 Null, 공백, 띄어쓰기를 사용하지 않습니다.")
    @JsonProperty("password")
    private String password;
}

