package com.example.decofolio.domain.comment.presentation.dto.response;



import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCommentResponse {

    private Long commentId;        // 댓글 ID
    private String content;        // 댓글 내용
    private String authorName;     // 작성자 이름
    private int authorAge;         // 작성자 나이
    private String authorLocation; // 작성자 거주지

    public CreateCommentResponse(Long commentId, String content, String authorName, int authorAge, String authorLocation) {
        this.commentId = commentId;
        this.content = content;
        this.authorName = authorName;
        this.authorAge = authorAge;
        this.authorLocation = authorLocation;
    }
}
