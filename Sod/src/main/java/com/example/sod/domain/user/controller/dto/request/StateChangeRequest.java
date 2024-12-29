package com.example.sod.domain.user.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class StateChangeRequest {

    @NotNull(message = "상태는 반드시 입력해야 합니다.")
    private String state;
}
