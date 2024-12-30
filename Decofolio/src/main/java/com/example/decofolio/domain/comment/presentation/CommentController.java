package com.example.decofolio.domain.comment.presentation;


import com.example.decofolio.domain.comment.presentation.dto.request.CreateCommentRequest;
import com.example.decofolio.domain.comment.presentation.dto.response.CreateCommentResponse;
import com.example.decofolio.domain.comment.service.CreateCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/v1/comments")
@RestController
public class CommentController {

    private final CreateCommentService createCommentService;

    //댓글 작성
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{meeting-id}")
    public CreateCommentResponse createComment(@PathVariable("meeting-id") Long meetingId, @RequestBody @Valid CreateCommentRequest request) {
        return createCommentService.execute(meetingId, request);
    }

}
