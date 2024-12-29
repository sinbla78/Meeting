package com.example.decofolio.domain.meeting.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MeetingSearchRequest {

    /**
     * 모임 제목 (검색 기준)
     * 제목을 기반으로 모임을 검색합니다.
     */
    @JsonProperty("title")
    private String title;
}
