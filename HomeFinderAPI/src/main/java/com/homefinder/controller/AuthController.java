package com.homefinder.controller;


import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.homefinder.model.User;
import com.homefinder.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.file.attribute.UserPrincipal;

@RestController
@RequestMapping("/auth")
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
    @PostMapping("/login")
    public String login(@RequestBody User user){
        System.out.println("We innnn");
        System.out.println(user.getEmail()+"   "+user.getPassword());
        String uri ="https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyDGoLjxXsnTNhwu9WrHOheIeZUdyG224Zc";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JsonObject properties = new JsonObject();
        properties.addProperty("email", user.getEmail());
        properties.addProperty("password", user.getPassword());
        properties.addProperty("returnSecureToken", true);
        HttpEntity<String> request = new HttpEntity<>(properties.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(uri, request, String.class);
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
