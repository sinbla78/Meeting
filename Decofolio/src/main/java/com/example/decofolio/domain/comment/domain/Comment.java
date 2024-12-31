package com.example.decofolio.domain.comment.domain;

import com.example.decofolio.domain.meeting.domain.Meeting;
import com.example.decofolio.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 댓글(Comment) 엔티티 클래스
 * - 모임(Meeting)과 사용자(User)에 연관된 댓글 정보를 관리
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자를 PROTECTED로 설정하여 외부에서 직접 생성하지 못하도록 제한
@Entity // JPA 엔티티 클래스임을 명시
@Table(name = "meeting_comment") // 데이터베이스 테이블 이름을 "meeting_comment"로 지정
public class Comment {

    @Id // 기본 키로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임 (AUTO_INCREMENT 방식)
    private Long id; // 댓글 ID

    @NotNull // null 값 허용 안 함
    @Size(max = 500) // 최대 500자까지 입력 가능
    private String content; // 댓글 내용

    /**
     * 사용자(User)와의 다대일 관계 설정
     * - 댓글 작성자를 나타냄
     */
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 설정 (필요한 시점에 데이터를 로드)
    @JoinColumn(name = "user_id", nullable = false) // 외래 키 이름을 "user_id"로 설정하며 null 값을 허용하지 않음
    private User user;

    /**
     * 모임(Meeting)과의 다대일 관계 설정
     * - 댓글이 속한 모임을 나타냄
     */
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 설정 (필요한 시점에 데이터를 로드)
    @JoinColumn(name = "meeting_id", nullable = false) // 외래 키 이름을 "meeting_id"로 설정하며 null 값을 허용하지 않음
    private Meeting meeting;

    /**
     * 댓글 생성자
     * - 빌더 패턴을 사용해 객체 생성 시 content, user, meeting 값을 반드시 설정하도록 강제
     *
     * @param content 댓글 내용
     * @param user 댓글 작성자 (User 엔티티)
     * @param meeting 댓글이 속한 모임 (Meeting 엔티티)
     */
    @Builder
    public Comment(String content, User user, Meeting meeting) {
        this.content = content;
        this.user = user;
        this.meeting = meeting;
    }
}
