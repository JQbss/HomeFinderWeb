package com.homefinder.controller;


import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.homefinder.model.User;
import com.homefinder.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;

@RestController
@RequestMapping("/auth/")
public class AuthController {
   final UserService userService;
    Authentication authentication;
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public UserRecord createUser(@RequestBody User user) throws FirebaseAuthException {
        return userService.addUser(user);
    }
    @GetMapping("/user-details")
    public ResponseEntity<User> getUserInfo() {
        User userPrincipal = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object principal = securityContext.getAuthentication().getPrincipal();
        if (principal instanceof User) {
            userPrincipal = ((User) principal);
        }
        System.out.println(userPrincipal);
        return ResponseEntity.ok(userPrincipal);
    }

}
