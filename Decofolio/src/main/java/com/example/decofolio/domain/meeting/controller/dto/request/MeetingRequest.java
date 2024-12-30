package com.example.decofolio.domain.meeting.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class MeetingRequest {

    /**
     * 모임 제목 (필수 항목)
     * 제목은 1자 이상 50자 이하로 설정
     */
    @NotBlank(message = "제목은 필수 항목입니다.")
    @Size(min = 1, max = 50, message = "제목은 3자 이상 20자 이하여야 합니다.")
    @JsonProperty("title")
    private String title;

    /**
     * 모임 내용 (필수 항목)
     * 내용은 10자 이상 250 이하로 설정
     */
    @NotBlank(message = "모임 내용은 필수 항목입니다.")
    @Size(min = 10, max = 250, message = "모임 내용은 10자 이상 1500자 이하여야 합니다.")
    @JsonProperty("text")
    private String text;

    /**
     * 모임 주소 (필수 항목)
     * 주소는 3자 이상 100자 이하로 설정
     */
    @NotBlank(message = "주소는 필수 항목입니다.")
    @Size(min = 3, max = 100, message = "주소는 3자 이상 100자 이하여야 합니다.")
    @JsonProperty("address")
    private String address;

}
