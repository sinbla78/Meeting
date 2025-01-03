package com.example.decofolio.domain.user.controller;

import com.example.decofolio.domain.user.controller.dto.request.SignUpRequest;
import com.example.decofolio.domain.user.controller.dto.request.StateChangeRequest;
import com.example.decofolio.domain.user.controller.dto.request.UpdatePasswordRequest;
import com.example.decofolio.domain.user.controller.dto.response.UserProfileResponse;
import com.example.decofolio.domain.user.domain.State;
import com.example.decofolio.domain.user.domain.User;
import com.example.decofolio.domain.user.service.SignUpService;
import com.example.decofolio.domain.user.service.UpdatePasswordService;
import com.example.decofolio.domain.user.service.UpdateStateService;
import com.example.decofolio.domain.user.service.UserDetailService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final SignUpService signUpService;
    private final UserDetailService userDetailService;
    private final UpdatePasswordService updatePasswordService;
    private final UpdateStateService updateStateService;

    /**
     * 사용자 프로필 정보 조회
     * @param userId 사용자 ID
     * @return 사용자 정보와 참여한 모임, 작성한 모임
     */
    @ApiOperation("마이페이지")
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserProfileResponse getUserProfile(@PathVariable Long userId) {
        return userDetailService.getUserProfile(userId);
    }

    /**
     * 회원 가입 처리
     * @param signUpRequest 회원 가입 요청
     */
    @ApiOperation("회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        signUpService.execute(signUpRequest);
    }

    /**
     * 비밀번호 업데이트 처리
     * @param updatePasswordRequest 비밀번호 변경 요청
     */
    @ApiOperation("비밀번호 변경")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/password")
    public void updatePassword(@RequestBody @Valid UpdatePasswordRequest updatePasswordRequest) {
        updatePasswordService.execute(updatePasswordRequest);
    }

    /**
     * 사용자 상태 변경 처리(회원 정지/회원 탈퇴)
     * @param userId 사용자 ID
     * @param stateChangeRequest 상태 변경 요청
     */
    @ApiOperation("사용자 상태")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{userId}/state")
    public void updateState(@PathVariable Long userId, @RequestBody @Valid StateChangeRequest stateChangeRequest) {
        State newState = State.valueOf(stateChangeRequest.getState().toUpperCase());
        updateStateService.updateState(userId, newState);
    }
}
