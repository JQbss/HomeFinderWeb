package com.homefinder.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.homefinder.model.User;
import com.homefinder.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<UserRecord> all(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    UserRecord one(@PathVariable String id) {
        return userService.findById(id);
                //.orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable String id) throws FirebaseAuthException {
        userService.deleteById(id);
    }

}