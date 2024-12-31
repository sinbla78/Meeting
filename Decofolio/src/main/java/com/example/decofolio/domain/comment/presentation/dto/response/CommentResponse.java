package com.example.decofolio.domain.comment.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponse {
    private Long commentId;
    private String content;
    private String authorName;
    private int authorAge;
    private String authorLocation;
}
