package com.example.decofolio.domain.user.service;

import com.example.decofolio.domain.user.domain.State;
import com.example.decofolio.domain.user.domain.User;
import com.example.decofolio.domain.user.domain.repository.UserRepository;
import com.example.decofolio.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
