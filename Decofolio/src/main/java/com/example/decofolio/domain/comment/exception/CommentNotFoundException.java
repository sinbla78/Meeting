package com.example.decofolio.domain.comment.exception;

import com.example.decofolio.global.error.exception.CustomException;
import com.example.decofolio.global.error.exception.ErrorCode;
/**
 * 댓글을 찾을 수 없을 때 발생하는 커스텀 예외
 * - 댓글 ID로 조회 시 존재하지 않을 경우 사용
 */
public class CommentNotFoundException extends CustomException {

    // 재사용 가능한 예외 인스턴스를 정적으로 선언
    public static final CustomException EXCEPTION =
            new CommentNotFoundException();

    /**
     * 기본 생성자
     * - ErrorCode.COMMENT_NOT_FOUND를 상위 클래스(CustomException)에 전달
     */
    private CommentNotFoundException() {
        super(ErrorCode.COMMENT_NOT_FOUND);
    }
}
