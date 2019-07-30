package com.example.patserfelices.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Set;

public interface CommentRepository extends JpaRepository <Comment, Long> {

    Set<Comment> findAllByPostId(Long postId);

    @Transactional
    void deleteByPostId(Long postId);
}
