package com.homefinder.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.homefinder.model.User;
import com.homefinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    public UserRecord createUser(@RequestBody User user) throws FirebaseAuthException {
        System.out.println("@@@@@@@@@We in create");
        return userService.addUser(user);
    }

}