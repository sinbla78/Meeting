package com.example.decofolio.domain.user.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class StateChangeRequest {

    @NotNull(message = "ACTIVE, INACTIVE, BANNED 중에 입력해야 합니다.")
    @JsonProperty("state")
    private String state;
}
