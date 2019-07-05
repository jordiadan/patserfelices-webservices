package com.example.patserfelices.user;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"/users/{username}"})
    public User getUserByUsername(@PathVariable String username) {
        return this.userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
