package com.example.patserfelices.auth.security;

import com.example.patserfelices.user.User;
import com.example.patserfelices.user.UserController;
import java.util.Collections;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AppUserDetailService implements UserDetailsService {
    private final UserController userController;

    public AppUserDetailService(UserController userController) {
        this.userController = userController;
    }

    public final UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userController.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        } else {
            return org.springframework.security.core.userdetails.User.withUsername(username).password(user.getPassword()).authorities(Collections.emptyList()).accountExpired(false).accountLocked(false).credentialsExpired(false).disabled(false).build();
        }
    }


}
