package com.example.patserfelices.posts;

import com.example.patserfelices.comment.CommentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin
public class PostController {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public PostController(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        List<Post> posts = this.postRepository.findAllByOrderByCreatedAtDesc();
        posts.forEach(post -> post.setNumberOfComments(getNumberCommentsByPostId(post.getId())));
        return posts;
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        // Save image before saving post, throw error in case of error to upload image (size, connection error...)
        System.out.println("Saving post...");
        post.setNumberOfComments(0L);
        System.out.println(post);
        return this.postRepository.save(post);
    }

    @PostMapping("posts/{postId}/like/{username}")
    public Set<String> likePost(@PathVariable Long postId, @PathVariable String username) {
        Optional<Post> postOptional = this.postRepository.findById(postId);
        if(postOptional.isPresent()) {
            Post post = postOptional.get();
            Set<String> likes = post.getLikes();
            if(likes.contains(username)){
                likes.remove(username);
            } else {
                likes.add(username);
            }
            this.postRepository.save(post);
            return likes;
        }
        return null;
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        System.out.println("Deleting post...");
        this.postRepository.deleteById(id);
        this.commentRepository.deleteByPostId(id);
    }

    @GetMapping("/posts/{postId}/comments/number")
    public Long getNumberCommentsByPostId(@PathVariable Long postId) {
        return (long) commentRepository.findAllByPostId(postId).size();
    }
}
