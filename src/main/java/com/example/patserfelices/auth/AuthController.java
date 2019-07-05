package com.example.patserfelices.auth;

import com.example.patserfelices.auth.security.jwt.TokenProvider;
import com.example.patserfelices.user.User;
import com.example.patserfelices.user.UserController;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthController {
    private final UserController userController;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserController userController, TokenProvider tokenProvider, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userController = userController;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        User user = new User();
        user.setUsername("admin");
        user.setName("Nombre");
        user.setFirstSurname("Apellido");
        user.setPassword(this.passwordEncoder.encode("admin"));
        user.setProfilePicture("https://ui-avatars.com/api/?rounded=true&name=" + user.getName() + "+" + user.getFirstSurname());
        this.userController.saveUser(user);
    }

    @GetMapping({"/authenticate"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void authenticate() {
    }

    @PostMapping({"/login"})
    public String logIn(@Valid @RequestBody User user, HttpServletResponse response) {
        System.out.println("Trying to log in...");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        try {
            this.authenticationManager.authenticate(authenticationToken);
            return this.tokenProvider.createToken(user.getUsername());
        } catch (AuthenticationException var5) {
            response.setStatus(401);
            return null;
        }
    }
}
