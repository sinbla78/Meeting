package com.example.decofolio.domain.meeting.controller.dto.response;

import com.example.decofolio.domain.meeting.domain.Meeting;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MeetingResponse {

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
     * Meeting 엔티티 객체를 MeetingResponse로 변환
     *
     * @param meeting 변환할 Meeting 객체
     * @return MeetingResponse 객체
     */
    public static MeetingResponse fromEntity(Meeting meeting) {
        MeetingResponse response = new MeetingResponse();
        response.meetingId = meeting.getMeetingId(); // 모임 ID
        response.title = meeting.getTitle(); // 모임 제목
        response.text = meeting.getText(); // 모임 내용
        response.imageUrl = meeting.getImageUrl(); // 이미지 URL
        response.address = meeting.getAddress(); // 모임 주소
        return response;
    }
}
