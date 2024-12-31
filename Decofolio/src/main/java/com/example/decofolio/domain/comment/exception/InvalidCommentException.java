package com.example.decofolio.domain.comment.exception;

import com.example.decofolio.global.error.exception.CustomException;
import com.example.decofolio.global.error.exception.ErrorCode;

/**
 * 댓글과 관련된 유효성 검증 예외
 * - 댓글이 잘못되었을 때 발생시키는 커스텀 예외 클래스
 */
public class InvalidCommentException extends CustomException {

    // 재사용 가능한 예외 인스턴스를 정적으로 선언
    public static final Exception EXCEPTION = new InvalidCommentException();

    /**
     * 기본 생성자
     * - ErrorCode.INVALID_COMMENT를 상위 클래스(CustomException)에 전달
     */
    private InvalidCommentException() {
        super(ErrorCode.INVALID_COMMENT);
    }
}
