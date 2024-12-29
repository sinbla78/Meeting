package com.example.decofolio.domain.user.service;

import com.example.decofolio.domain.meeting.controller.dto.response.MeetingResponse;
import com.example.decofolio.domain.user.controller.dto.response.UserProfileResponse;
import com.example.decofolio.domain.user.domain.User;
import com.example.decofolio.domain.user.domain.repository.UserRepository;
import com.example.decofolio.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailService {

    private final UserRepository userRepository;

    /**
     * 사용자 정보와 참여한 모임을 가져오는 메서드
     * @param userId 사용자 ID
     * @return 사용자 정보와 참여한 모임
     */
    public UserProfileResponse getUserProfile(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return UserProfileResponse.from(userOptional.get());
        } else {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
    }

}
