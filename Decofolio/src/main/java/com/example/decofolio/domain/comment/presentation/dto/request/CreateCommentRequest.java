package com.example.decofolio.domain.comment.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter // 클래스의 모든 필드에 대한 Getter 메서드를 자동 생성
public class CreateCommentRequest {

    @NotNull(message = "content는 Null을 허용하지 않습니다.") // 값이 반드시 있어야 함을 명시
    private String content; // 작성될 댓글의 내용

    /**
     * 댓글 생성 요청 DTO
     * - 클라이언트가 서버로 댓글을 생성할 때 필요한 데이터를 전달합니다.
     *
     * Validation:
     * - content: Null 값이 허용되지 않습니다.
     */
}
