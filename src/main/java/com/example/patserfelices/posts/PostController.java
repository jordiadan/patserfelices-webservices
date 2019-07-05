package com.example.patserfelices.posts;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PostController {
    private PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping({"/posts"})
    public List<Post> getAllPosts() {
        return this.postRepository.findAllByOrderByCreatedAtDesc();
    }

    @PostMapping({"/posts"})
    public void createPost(@RequestBody Post post) {
        System.out.println("Saving post...");
        System.out.println(post);
        this.postRepository.save(post);
    }
}
