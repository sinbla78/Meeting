package com.example.decofolio.domain.meeting.controller;

import com.example.decofolio.domain.meeting.controller.dto.request.MeetingRequest;
import com.example.decofolio.domain.meeting.controller.dto.response.MeetingDetailResponse;
import com.example.decofolio.domain.meeting.controller.dto.response.MeetingResponse;
import com.example.decofolio.domain.meeting.domain.Meeting;
import com.example.decofolio.domain.meeting.service.MeetingService;
import com.example.decofolio.domain.meeting.service.S3Service;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1/meeting")
@RestController
public class MeetingController {

    private final MeetingService meetingService;
    private final S3Service s3Service;

    /**
     * 모임 제목 검색 API
     *
     * @param title 검색할 모임 제목 (선택)
     * @return 검색된 모임 목록
     */
    @ApiOperation(value = "모임 검색(제목)")
    @ResponseStatus(HttpStatus.OK)
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
    @ApiOperation(value = "모임 상세 조회")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{meetingId}")
    public ResponseEntity<MeetingDetailResponse> getMeetingDetail(@PathVariable Long meetingId) {
        MeetingDetailResponse response = meetingService.getMeetingDetail(meetingId);
        return ResponseEntity.ok(response);
    }

    /**
     * 모임 생성 및 이미지 업로드
     *
     * @param meetingRequest 모임 생성 요청 정보
     * @param file           모임 이미지 (optional)
     * @return 생성된 모임 정보
     */
    @ApiOperation("모임 생성")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<MeetingResponse> createMeeting(
            @RequestPart("meetingRequest") @Valid MeetingRequest meetingRequest,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        // 모임 생성 및 이미지 업로드
        Meeting meeting = meetingService.execute(meetingRequest, file);

        // 생성된 모임을 응답으로 반환
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MeetingResponse.fromEntity(meeting));  // MeetingResponse 객체로 변환 후 반환
    }

    /**
     * 모임 참가 API
     *
     * @param meetingId 참가하려는 모임의 ID
     * @return 참가 성공 응답
     */
    @ApiOperation("모임 참가")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{meetingId}/join")
    public ResponseEntity<Void> joinMeeting(@PathVariable Long meetingId) {
        meetingService.joinMeeting(meetingId);
        return ResponseEntity.ok().build();
    }

}
