package com.example.decofolio.domain.comment.service;

import com.example.decofolio.domain.comment.domain.Comment;
import com.example.decofolio.domain.comment.domain.repository.CommentRepository;
import com.example.decofolio.domain.comment.presentation.dto.request.CreateCommentRequest;
import com.example.decofolio.domain.comment.presentation.dto.response.CommentResponse;
import com.example.decofolio.domain.comment.presentation.dto.response.CreateCommentResponse;
import com.example.decofolio.domain.meeting.domain.Meeting;
import com.example.decofolio.domain.meeting.domain.repository.MeetingRepository;
import com.example.decofolio.domain.meeting.exception.MeetingNotFoundException;
import com.example.decofolio.domain.user.domain.User;
import com.example.decofolio.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateCommentService {

    private final UserFacade userFacade;
    private final MeetingRepository meetingRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CreateCommentResponse execute(Long meetingId, CreateCommentRequest request) {
        User user = userFacade.getCurrentUser();

        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> MeetingNotFoundException.EXCEPTION);

        Comment comment = Comment.builder()
                .meeting(meeting)
                .user(user)
                .content(request.getContent())
                .build();

        commentRepository.save(comment);

        return new CreateCommentResponse(
                comment.getId(),
                comment.getContent(),
                user.getName(),         // 작성자 이름
                user.getAge(),          // 작성자 나이
                user.getLocation()      // 작성자 거주지
        );
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByMeeting(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> MeetingNotFoundException.EXCEPTION);

        // 댓글 리스트 조회
        List<Comment> comments = commentRepository.findAllByMeeting(meeting);

        // DTO 변환
        return comments.stream()
                .map(comment -> new CommentResponse(
                        comment.getId(),
                        comment.getContent(),
                        comment.getUser().getName(),     // 작성자 이름
                        comment.getUser().getAge(),      // 작성자 나이
                        comment.getUser().getLocation()  // 작성자 거주지
                ))
                .collect(Collectors.toList()); // 수정된 부분
    }
}