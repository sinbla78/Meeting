package com.example.decofolio.domain.meeting.domain;

import com.example.decofolio.domain.user.domain.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingId;

    /**
     * 모임 제목
     * 최소 1자 이상, 최대 50자
     */
    @Column(length = 50, nullable = false)
    @Size(min = 1, max = 50, message = "모임 제목은 5자 이상 50자 이하여야 합니다.")
    private String title;

    /**
     * 모임 내용
     * 최소 10자 이상, 최대 250자
     */
    @Column(length = 250, nullable = false)
    @Size(min = 10, max = 250, message = "모임 내용은 10자 이상 255자 이하여야 합니다.")
    private String text;

    /**
     * 이미지 URL (선택 항목)
     * null일 경우 기본 회색 배경
     */
    @Column(nullable = true)
    private String imageUrl;

    /**
     * 모임 주소
     * 최소 3자 이상, 최대 100자
     */
    @Column(length = 100, nullable = false)
    @Size(min = 3, max = 100, message = "주소는 최소 3자 이상 100자 이하여야 합니다.")
    private String address;

    /**
     * 모임 주최자
     * 해당 필드는 null일 수 없음
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 모임 생성 시각
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * 모임 수정 시각
     */
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * 모임 참가자 목록
     */
    @ManyToMany
    @JoinTable(
            name = "meeting_participants",
            joinColumns = @JoinColumn(name = "meeting_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> participants = new HashSet<>();

    /**
     * Meeting 생성자
     *
     * @param title       모임 제목
     * @param text        모임 내용
     * @param imageUrl    이미지 URL
     * @param address     모임 주소
     * @param user        모임 주최자
     */
    @Builder
    public Meeting(String title, String text, String imageUrl, String address, User user) {
        this.title = title;
        this.text = text;
        this.imageUrl = imageUrl;
        this.address = address;
        this.user = user;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 모임에 참가자를 추가합니다.
     *
     * @param user 참가할 사용자
     * @throws IllegalArgumentException 이미 참가한 사용자일 경우 예외 발생
     */
    public void addParticipant(User user) {
        if (participants.contains(user)) {
            throw new IllegalArgumentException("이미 참가한 사용자입니다.");
        }
        participants.add(user);
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        this.updatedAt = LocalDateTime.now();
    }


}
