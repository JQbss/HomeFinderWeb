package com.homefinder.controller;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.gson.JsonObject;
import com.homefinder.model.User;
import com.homefinder.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    final UserService userService;
    private final FirebaseAuth firebaseAuth;

    Authentication authentication;
    public AuthController(UserService userService, FirebaseAuth firebaseAuth) {
        this.userService = userService;
        this.firebaseAuth = firebaseAuth;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<User> createUser(@RequestBody User user) throws FirebaseAuthException {
        userService.addUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getUid())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public String login(@RequestBody User user){
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

    @RequestMapping(method = RequestMethod.GET, path = "/user-details")
    public ResponseEntity<User> getUserInfo() {
        User userPrincipal = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object principal = securityContext.getAuthentication().getPrincipal();
        if (principal instanceof User) {
            userPrincipal = ((User) principal);
        }
        return ResponseEntity.ok(userPrincipal);
    }

//    @RequestMapping(method = RequestMethod.GET, path = "/token")
//    public Map<String, String> refreshToken() throws FirebaseAuthException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//
//        //joining elements of collections as comma seperated string
//        String authorities = authentication.getAuthorities()
//                .stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//        System.out.println(authorities);
//        String customToken = firebaseAuth.createCustomToken("test", Collections.singletonMap("authorities", authorities));
//        return Collections.singletonMap("token", customToken);
//    }
}
