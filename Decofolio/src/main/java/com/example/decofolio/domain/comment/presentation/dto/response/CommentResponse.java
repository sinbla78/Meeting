package com.example.decofolio.domain.comment.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter // 모든 필드에 대한 Getter 메서드를 자동 생성
@AllArgsConstructor // 모든 필드를 초기화하는 생성자를 자동 생성
public class CommentResponse {

    private Long commentId;        // 댓글 ID (고유 식별자)
    private String content;        // 댓글 내용
    private String authorName;     // 댓글 작성자의 이름
    private int authorAge;         // 댓글 작성자의 나이
    private String authorLocation; // 댓글 작성자의 거주지

    /**
     * 댓글 응답 DTO
     * - 클라이언트에 댓글 정보를 반환할 때 사용
     *
     * @param commentId 댓글의 고유 ID
     * @param content 댓글 내용
     * @param authorName 작성자 이름
     * @param authorAge 작성자 나이
     * @param authorLocation 작성자 거주지
     */
}
