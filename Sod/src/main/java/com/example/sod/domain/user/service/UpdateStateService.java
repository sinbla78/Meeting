package com.example.sod.domain.user.service;

import com.example.sod.domain.user.domain.User;
import com.example.sod.domain.user.domain.State;
import com.example.sod.domain.user.domain.repository.UserRepository;
import com.example.sod.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.sod.global.error.exception.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UpdateStateService {

    private final UserRepository userRepository;

    @Transactional
    public void updateState(Long userId, State newState) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION); // 예외 클래스 수정

        user.changeState(newState);
        userRepository.save(user); // 상태 업데이트 후 저장
    }
}
