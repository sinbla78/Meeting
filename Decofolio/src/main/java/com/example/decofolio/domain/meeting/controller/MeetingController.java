package com.example.decofolio.domain.meeting.controller;

import com.example.decofolio.domain.meeting.controller.dto.request.MeetingRequest;
import com.example.decofolio.domain.meeting.controller.dto.response.MeetingDetailResponse;
import com.example.decofolio.domain.meeting.controller.dto.response.MeetingResponse;
import com.example.decofolio.domain.meeting.domain.Meeting;
import com.example.decofolio.domain.meeting.service.MeetingService;
import com.example.decofolio.domain.meeting.service.S3Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1/meeting")
@RestController
@Tag(name = "Meeting", description = "모임")
public class MeetingController {

    private final MeetingService meetingService;
    private final S3Service s3Service;

    /**
     * 모임 제목 검색 API
     *
     * @param title 검색할 모임 제목 (선택)
     * @return 검색된 모임 목록
     */
    @Operation(summary = "모임 검색(제목 기반)", description = "receive meetings by title")
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
    @Operation(summary = "모임 조회", description = "receive meetings")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{meetingId}")
    public ResponseEntity<MeetingDetailResponse> getMeetingDetail(@PathVariable Long meetingId) {
        MeetingDetailResponse response = meetingService.getMeetingDetail(meetingId);
        return ResponseEntity.ok(response);
    }

    /**
     * 모임 생성 및 이미지 업로드
     *
     * @param meetingRequestJson 모임 생성 요청 정보
     * @param file           모임 이미지 (optional)
     * @return 생성된 모임 정보
     */
    @Operation(summary = "모임 생성 (이미지 포함)", description = "JSON 데이터와 이미지를 함께 업로드합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "모임 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MeetingResponse> createMeeting(
            @RequestPart(value = "meetingRequest")
            @Valid @Parameter(description = "모임 정보(JSON 형식)")
            String meetingRequestJson,

            @RequestPart(value = "file", required = false)
            @Parameter(description = "업로드할 파일")
            MultipartFile file) {

        ObjectMapper objectMapper = new ObjectMapper();
        MeetingRequest meetingRequest;
        try {
            meetingRequest = objectMapper.readValue(meetingRequestJson, MeetingRequest.class);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // JSON 파싱 오류 처리
        }

        Meeting meeting = meetingService.execute(meetingRequest, file);

        return ResponseEntity.status(HttpStatus.CREATED).body(MeetingResponse.fromEntity(meeting));
    }



    /**
     * 모임 참가 API
     *
     * @param meetingId 참가하려는 모임의 ID
     * @return 참가 성공 응답
     */
    @Operation(summary = "모임 참가", description = "join meeting")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{meetingId}/join")
    public ResponseEntity<Void> joinMeeting(@PathVariable Long meetingId) {
        meetingService.joinMeeting(meetingId);
        return ResponseEntity.ok().build();
    }

}
