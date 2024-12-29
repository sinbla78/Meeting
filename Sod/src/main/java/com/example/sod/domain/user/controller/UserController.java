package com.example.sod.domain.user.controller;

import com.example.sod.domain.user.controller.dto.request.SignUpRequest;
import com.example.sod.domain.user.controller.dto.request.StateChangeRequest;
import com.example.sod.domain.user.controller.dto.request.UpdatePasswordRequest;
import com.example.sod.domain.user.domain.State;
import com.example.sod.domain.user.service.SignUpService;
import com.example.sod.domain.user.service.UpdatePasswordService;
import com.example.sod.domain.user.service.UpdateStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final SignUpService signUpService;
    private final UpdatePasswordService updatePasswordService;
    private final UpdateStateService updateStateService;

    /**
     * 회원 가입 처리
     * @param signUpRequest 회원 가입 요청
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        signUpService.execute(signUpRequest);
    }

    /**
     * 비밀번호 업데이트 처리
     * @param updatePasswordRequest 비밀번호 변경 요청
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    public void updatePassword(@RequestBody @Valid UpdatePasswordRequest updatePasswordRequest) {
        updatePasswordService.execute(updatePasswordRequest);
    }

    /**
     * 사용자 상태 변경 처리(회원 정지/회원 탈퇴)
     * @param userId 사용자 ID
     * @param stateChangeRequest 상태 변경 요청
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{userId}/state")
    public void updateState(@PathVariable Long userId, @RequestBody @Valid StateChangeRequest stateChangeRequest) {
        State newState = State.valueOf(stateChangeRequest.getState().toUpperCase());
        updateStateService.updateState(userId, newState);
    }
}
