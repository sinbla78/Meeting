package com.example.decofolio.domain.meeting.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MeetingDetailResponse {

    /**
     * 모임 ID
     */
    @JsonProperty("meetingId")
    private Long meetingId;

    /**
     * 모임 제목
     */
    @JsonProperty("title")
    private String title;

    /**
     * 모임 내용
     */
    @JsonProperty("text")
    private String text;

    /**
     * 모임 이미지 URL
     */
    @JsonProperty("imageUrl")
    private String imageUrl;

    /**
     * 모임 주소
     */
    @JsonProperty("address")
    private String address;

    /**
     * 참여자 정보
     */
    @JsonProperty("participants")
    private List<ParticipantInfo> participants;

    /**
     * 작성자 정보
     */
    @JsonProperty("creator")
    private CreatorInfo creator;

    @Getter
    @Builder
    public static class ParticipantInfo {
        @JsonProperty("userId")
        private Long userId;    // 사용자 ID
        @JsonProperty("name")
        private String name; // 사용자 이름
    }

    @Getter
    @Builder
    public static class CreatorInfo {
        @JsonProperty("userId")
        private Long userId;    // 작성자 ID
        @JsonProperty("name")
        private String name; // 작성자 이름
    }
}
