package com.example.decofolio.domain.comment.presentation.dto.response;



import lombok.Builder;
import lombok.Getter;

@Getter // 클래스의 모든 필드에 대해 Getter 메서드를 자동 생성
@Builder // 빌더 패턴을 사용하여 객체 생성 지원
public class CreateCommentResponse {

    private Long commentId;        // 댓글 ID (고유 식별자)
    private String content;        // 댓글 내용
    private String authorName;     // 댓글 작성자의 이름
    private int authorAge;         // 댓글 작성자의 나이
    private String authorLocation; // 댓글 작성자의 거주지

    /**
     * 모든 필드를 초기화하는 생성자
     *
     * @param commentId 댓글 ID
     * @param content 댓글 내용
     * @param authorName 작성자 이름
     * @param authorAge 작성자 나이
     * @param authorLocation 작성자 거주지
     */
    public CreateCommentResponse(Long commentId, String content, String authorName, int authorAge, String authorLocation) {
        this.commentId = commentId;
        this.content = content;
        this.authorName = authorName;
        this.authorAge = authorAge;
        this.authorLocation = authorLocation;
    }
}
