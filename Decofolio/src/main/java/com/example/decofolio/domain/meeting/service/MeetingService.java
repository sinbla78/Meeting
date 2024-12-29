package com.example.decofolio.domain.meeting.service;

import com.example.decofolio.domain.meeting.controller.dto.request.MeetingRequest;
import com.example.decofolio.domain.meeting.controller.dto.response.MeetingDetailResponse;
import com.example.decofolio.domain.meeting.controller.dto.response.MeetingResponse;
import com.example.decofolio.domain.meeting.domain.repository.MeetingRepository;
import com.example.decofolio.domain.meeting.domain.Meeting;
import com.example.decofolio.domain.meeting.exception.MeetingNotFoundException;
import com.example.decofolio.domain.meeting.exception.UserAlreadyParticipatingException;
import com.example.decofolio.domain.user.domain.User;
import com.example.decofolio.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;  // 모임 데이터를 저장할 repository
    private final UserFacade userFacade;  // 현재 사용자의 정보를 가져오는 facade

    /**
     * 모임 제목으로 검색하는 메서드
     *
     * @param title 검색할 모임 제목
     * @return 검색된 모임 목록
     */
    @Transactional(readOnly = true)
    public Page<MeetingResponse> searchMeetingByTitle(String title, Pageable pageable) {
        // 페이지네이션을 포함한 Meeting 검색
        Page<Meeting> meetings = meetingRepository.findByTitleContainingIgnoreCase(title, pageable);

        // Meeting 엔티티를 MeetingResponse로 변환
        return meetings.map(MeetingResponse::fromEntity);
    }

    @Transactional(readOnly = true)
    public MeetingDetailResponse getMeetingDetail(Long meetingId) {
        // 모임 엔티티 조회
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("모임을 찾을 수 없습니다."));

        // 작성자 정보 추출
        User creator = meeting.getUser();

        // 참가자 목록 추출
        List<MeetingDetailResponse.ParticipantInfo> participants = meeting.getParticipants().stream()
                .map(user -> MeetingDetailResponse.ParticipantInfo.builder()
                        .userId(user.getId())
                        .name(user.getName())
                        .build())
                .collect(Collectors.toList());

        // 작성자 정보 설정
        MeetingDetailResponse.CreatorInfo creatorInfo = MeetingDetailResponse.CreatorInfo.builder()
                .userId(creator.getId())
                .name(creator.getName())
                .build();

        // DTO 반환
        return MeetingDetailResponse.builder()
                .meetingId(meeting.getMeetingId())
                .title(meeting.getTitle())
                .text(meeting.getText())
                .imageUrl(meeting.getImageUrl())
                .address(meeting.getAddress())
                .participants(participants)
                .creator(creatorInfo)
                .build();
    }


    /**
     * 모임을 생성하는 메서드
     *
     * @param meetingRequest 모임 생성에 필요한 정보가 담긴 DTO
     * @return 생성된 모임 객체
     */
    @Transactional
    public Meeting execute(MeetingRequest meetingRequest) {
        // 현재 로그인한 사용자 정보 가져오기
        User user = userFacade.getCurrentUser();

        // 사용자가 이미 모임을 생성하거나 참가했으면 예외 발생
        if (user.getCreatedMeeting() != null || user.getParticipatedMeeting() != null) {
            throw UserAlreadyParticipatingException.EXCEPTION;
        }

        // 모임 객체 생성
        Meeting meeting = Meeting.builder()
                .title(meetingRequest.getTitle())  // 모임 제목
                .text(meetingRequest.getText())  // 모임 설명
                .imageUrl(meetingRequest.getImageUrl())  // 모임 이미지 URL
                .address(meetingRequest.getAddress())  // 모임 주소
                .user(user)  // 모임을 생성한 사용자
                .build();

        // 사용자가 생성한 모임 및 참가한 모임에 설정
        user.setCreatedMeeting(meeting);
        user.setParticipatedMeeting(meeting);

        // 사용자 모임 참가 추가
        meeting.addParticipant(user);

        // 모임 정보 저장 후 반환
        return meetingRepository.save(meeting);
    }

    /**
     * 사용자가 모임에 참가하는 메서드
     *
     * @param meetingId 참가하려는 모임의 ID
     */
    @Transactional
    public void joinMeeting(Long meetingId) {
        // 현재 로그인한 사용자 정보 가져오기
        User currentUser = userFacade.getCurrentUser();

        // 사용자가 이미 모임을 생성하거나 참가했으면 예외 발생
        if (currentUser.getCreatedMeeting() != null || currentUser.getParticipatedMeeting() != null) {
            throw UserAlreadyParticipatingException.EXCEPTION;
        }

        // 모임 ID로 모임 찾기, 없으면 예외 발생
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> MeetingNotFoundException.EXCEPTION);

        // 모임 참가 처리
        meeting.addParticipant(currentUser);

        // 사용자에게 참가한 모임 설정
        currentUser.setParticipatedMeeting(meeting);
    }

}
