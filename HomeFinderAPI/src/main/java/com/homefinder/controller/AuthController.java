package com.homefinder.controller;


import com.google.firebase.auth.FirebaseAuthException;
import com.google.gson.JsonObject;
import com.homefinder.dto.LoginRequest;
import com.homefinder.model.User;
import com.homefinder.service.AuthService;
import com.homefinder.service.UserService;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    final UserService userService;
    final AuthService authService;
    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<?> createUser(@RequestBody LoginRequest loginRequest) throws FirebaseAuthException {
        userService.addUser(loginRequest);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        String uri ="https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyDGoLjxXsnTNhwu9WrHOheIeZUdyG224Zc";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JsonObject properties = new JsonObject();
        properties.addProperty("email", loginRequest.getEmail());
        properties.addProperty("password", loginRequest.getPassword());
        properties.addProperty("returnSecureToken", true);
        HttpEntity<String> request = new HttpEntity<>(properties.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(uri, request, String.class);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user-details")
    public ResponseEntity<User> getUserInfo() {
        return ResponseEntity.ok(authService.getUser());
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
