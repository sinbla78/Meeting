package com.example.decofolio.domain.user.controller.dto.response;

import com.example.decofolio.domain.user.domain.User;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Getter
@Builder
public class UserProfileResponse {

    /**
     * 사용자 ID
     */
    @JsonProperty("userId")
    private Long userId;

    /**
     * 사용자 아이디
     */
    @JsonProperty("accountId")
    private String accountId;

    /**
     * 사용자 이름
     */
    @JsonProperty("name")
    private String name;

    /**
     * 사용자 나이
     */
    @JsonProperty("age")
    private Integer age;

    /**
     * 사용자 거주지
     */
    @JsonProperty("location")
    private String location;

    /**
     * 사용자 상태
     */
    @JsonProperty("state")
    private String state;

    /**
     * 마지막 로그인 시간
     */
    @JsonProperty("lastLoginTime")
    private String lastLoginTime;

    /**
     * 참여한 모임
     */
    @JsonProperty("participatedMeeting")
    private MeetingResponse participatedMeeting;

    /**
     * 작성한 모임
     */
    @JsonProperty("createdMeeting")
    private MeetingResponse createdMeeting;

    @Getter
    @Builder
    public static class MeetingResponse {
        @JsonProperty("meetingId")
        private Long meetingId;

        @JsonProperty("title")
        private String title;
    }

    // User 엔티티를 기반으로 UserProfileResponse 생성
    public static UserProfileResponse from(User user) {
        MeetingResponse participatedMeeting = user.getParticipatedMeeting() != null
                ? new MeetingResponse(user.getParticipatedMeeting().getMeetingId(), user.getParticipatedMeeting().getTitle())
                : null;

        MeetingResponse createdMeeting = user.getCreatedMeeting() != null
                ? new MeetingResponse(user.getCreatedMeeting().getMeetingId(), user.getCreatedMeeting().getTitle())
                : null;

        return new UserProfileResponse(
                user.getId(),
                user.getAccountId(),
                user.getName(),
                user.getAge(),
                user.getLocation(),
                user.getState().name(),
                user.getLastLoginTime() != null ? user.getLastLoginTime().toString() : null,
                participatedMeeting,
                createdMeeting
        );
    }
}
