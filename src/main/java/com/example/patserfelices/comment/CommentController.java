package com.example.patserfelices.comment;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CommentController {

    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/posts/{postId}/comments")
    public Set<Comment> getCommentsByPostId(@PathVariable Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

//    @GetMapping("/posts/{postId}/comments/number")
//    public Long getNumberCommentsByPostId(@PathVariable Long postId) {
//        return (long) commentRepository.findAllByPostId(postId).size();
//    }

    @PostMapping("/comments")
    public Comment postComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
