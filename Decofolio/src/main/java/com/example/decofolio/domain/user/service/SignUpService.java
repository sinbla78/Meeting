package com.example.decofolio.domain.user.service;

import com.example.decofolio.domain.user.controller.dto.request.SignUpRequest;
import com.example.decofolio.domain.user.domain.State;
import com.example.decofolio.domain.user.domain.User;
import com.example.decofolio.domain.user.domain.repository.UserRepository;
import com.example.decofolio.domain.user.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(SignUpRequest signUpRequest) {

        // 계정 아이디가 이미 존재하는지 확인
        if (userRepository.findByAccountId(signUpRequest.getAccountId()).isPresent()) {
            throw UserAlreadyExistsException.EXCEPTION;
        }

        // User 객체 생성, password는 암호화하고 state는 Active로 설정
        User user = User.builder()
                .accountId(signUpRequest.getAccountId())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .name(signUpRequest.getName())
                .age(signUpRequest.getAge())
                .location(signUpRequest.getLocation())
                .state(State.ACTIVE) // state는 무조건 Active로 설정
                .build();

        // User 저장
        userRepository.save(user);
    }
}
