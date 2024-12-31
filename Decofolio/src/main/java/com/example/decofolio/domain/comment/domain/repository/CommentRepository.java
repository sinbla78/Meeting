package com.example.decofolio.domain.comment.domain.repository;


import com.example.decofolio.domain.comment.domain.Comment;
import com.example.decofolio.domain.meeting.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByMeeting(Meeting meeting);
}
