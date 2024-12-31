package com.example.decofolio.domain.comment.presentation;


import com.example.decofolio.domain.comment.presentation.dto.request.CreateCommentRequest;
import com.example.decofolio.domain.comment.presentation.dto.response.CommentResponse;
import com.example.decofolio.domain.comment.presentation.dto.response.CreateCommentResponse;
import com.example.decofolio.domain.comment.service.CreateCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1/comments")
@RestController
public class CommentController {

    private final CreateCommentService createCommentService;

    //댓글 작성
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{meetingId}")
    public CreateCommentResponse createComment(@PathVariable("meetingId") Long meetingId, @RequestBody @Valid CreateCommentRequest request) {
        return createCommentService.execute(meetingId, request);
    }

    @GetMapping("/{meetingId}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long meetingId) {
        List<CommentResponse> comments = createCommentService.getCommentsByMeeting(meetingId);
        return ResponseEntity.ok(comments);
    }

}
