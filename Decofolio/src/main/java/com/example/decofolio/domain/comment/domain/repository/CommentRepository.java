package com.example.decofolio.domain.comment.domain.repository;


import com.example.decofolio.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
