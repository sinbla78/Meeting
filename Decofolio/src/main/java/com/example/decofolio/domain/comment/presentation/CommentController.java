package com.example.decofolio.domain.comment.presentation;


import com.example.decofolio.domain.comment.presentation.dto.request.CreateCommentRequest;
import com.example.decofolio.domain.comment.presentation.dto.response.CommentResponse;
import com.example.decofolio.domain.comment.presentation.dto.response.CreateCommentResponse;
import com.example.decofolio.domain.comment.service.CreateCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1/comments")
@RestController
@Tag(name = "Comment", description = "회고")
public class CommentController {

    private final CreateCommentService createCommentService;

    /**
     * - 댓글 작성 API
     * - HTTP POST 요청
     * - 특정 모임(meetingId)에 댓글을 작성
     *
     * @param meetingId 댓글을 작성할 모임의 ID
     * @param request   댓글 작성 요청 데이터
     * @return          생성된 댓글에 대한 응답 데이터
     */
    @Operation(summary = "회고 작성", description = "create comment")
    @ResponseStatus(HttpStatus.CREATED) // HTTP 201 상태 반환
    @PostMapping("/{meetingId}") // /v1/comments/{meetingId}로 요청 시 매핑
    public CreateCommentResponse createComment(
            @PathVariable("meetingId") Long meetingId, // URL 경로에서 meetingId 추출
            @RequestBody @Valid CreateCommentRequest request // 요청 본문에서 댓글 데이터 추출 및 유효성 검증
    ) {
        return createCommentService.execute(meetingId, request); // 서비스 호출
    }

    /**
     * - 특정 모임의 댓글 조회 API
     * - HTTP GET 요청
     *
     * @param meetingId 댓글을 조회할 모임의 ID
     * @return          댓글 리스트를 포함한 응답 객체
     */
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "회고 불러오기", description = "get comment")
    @GetMapping("/{meetingId}") // /v1/comments/{meetingId}로 요청 시 매핑
    public ResponseEntity<List<CommentResponse>> getComments(
            @PathVariable Long meetingId // URL 경로에서 meetingId 추출
    ) {
        // 서비스 호출로 특정 모임의 댓글 리스트 조회
        List<CommentResponse> comments = createCommentService.getCommentsByMeeting(meetingId);
        return ResponseEntity.ok(comments); // HTTP 200 상태와 함께 댓글 리스트 반환
    }

}
