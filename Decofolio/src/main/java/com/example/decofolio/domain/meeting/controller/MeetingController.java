package com.example.decofolio.domain.meeting.controller;

import com.example.decofolio.domain.meeting.controller.dto.request.MeetingRequest;
import com.example.decofolio.domain.meeting.controller.dto.response.MeetingDetailResponse;
import com.example.decofolio.domain.meeting.controller.dto.response.MeetingResponse;
import com.example.decofolio.domain.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1/meeting")
@RestController
public class MeetingController {

    private final MeetingService meetingService;

    /**
     * 모임 제목 검색 API
     *
     * @param title 검색할 모임 제목 (선택)
     * @return 검색된 모임 목록
     */
    @GetMapping("/search")
    public Page<MeetingResponse> searchMeetings(
            @RequestParam String title,
            Pageable pageable
    ) {
        return meetingService.searchMeetingByTitle(title, pageable);
    }

    /**
     * 모임 상세 조회 API
     * @param meetingId 모임 ID
     * @return 모임 상세 정보
     */
    @GetMapping("/{meetingId}")
    public ResponseEntity<MeetingDetailResponse> getMeetingDetail(@PathVariable Long meetingId) {
        MeetingDetailResponse response = meetingService.getMeetingDetail(meetingId);
        return ResponseEntity.ok(response);
    }

    /**
     * 모임 생성 API
     *
     * @param meetingRequest 모임 생성 정보
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createMeeting(@RequestBody @Valid MeetingRequest meetingRequest) {
        meetingService.execute(meetingRequest);
    }

    /**
     * 모임 참가 API
     *
     * @param meetingId 참가하려는 모임의 ID
     * @return 참가 성공 응답
     */
    @PostMapping("/{meetingId}/join")
    public ResponseEntity<Void> joinMeeting(@PathVariable Long meetingId) {
        meetingService.joinMeeting(meetingId);
        return ResponseEntity.ok().build();
    }

}
