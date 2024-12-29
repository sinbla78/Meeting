package com.example.decofolio.domain.user.domain;

import com.example.decofolio.domain.meeting.domain.Meeting;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    @Size(min = 5, max = 20, message = "아이디는 5자 이상 20자 이하여야 합니다.")
    private String accountId;

    @Column(length = 60, nullable = false)
    @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
    private String password;

    @Column(length = 30, nullable = false)
    @Size(min = 2, max = 30, message = "이름은 2자 이상 30자 이하여야 합니다.")
    private String name;

    @Column(length = 50, nullable = false)
    @Min(value = 1, message = "나이는 1살 이상이어야 합니다.")
    @Max(value = 120, message = "나이는 120살 이하여야 합니다.")
    private Integer age;

    @Column(length = 50, nullable = false)
    @Size(min = 3, max = 50, message = "거주지는 최소 3자 이상 50자 이하여야 합니다.")
    private String location;

    @Enumerated(EnumType.STRING) // Enum 값을 문자열로 저장
    @Column(length = 10, nullable = false)
    private State state;

    @Column(nullable = true)
    private LocalDateTime lastLoginTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participated_meeting_id")
    private Meeting participatedMeeting; // 참가 중인 모임

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Meeting createdMeeting;
    @Builder
    private User(String accountId, String password, String name, Integer age, String location, State state) {
        this.accountId = accountId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.location = location;
        this.state = state;
    }
    public void setParticipatedMeeting(Meeting meeting) {
        this.participatedMeeting = meeting;
    }
    public void setCreatedMeeting(Meeting meeting) {
        this.createdMeeting = meeting;
    }
    public void updatePassword(String password) {
        this.password = password;
    }
    public void updateLastLoginTime() {
        this.lastLoginTime = LocalDateTime.now();
    }
    public void changeState(State newState) {
        this.state = newState;
    }

}
