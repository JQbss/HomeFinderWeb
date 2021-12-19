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
@RequestMapping("/api/")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/users")
//    List<UserRecord> all(){
//        return userService.findAll();
//    }



    @GetMapping("/users/{id}")
    UserRecord one(@PathVariable String id) {
        return userService.findById(id);
                //.orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @DeleteMapping("/users/{id}")
    void deleteEmployee(@PathVariable String id) throws FirebaseAuthException {
        userService.deleteById(id);
    }

}