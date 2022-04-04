package com.homefinder.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.homefinder.model.User;
import com.homefinder.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws FirebaseAuthException {
        userService.addUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getUid())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping
    public DeferredResult<ResponseEntity<String>> all(){
        DeferredResult<ResponseEntity<String>> result = new DeferredResult<>();
        this.userService.findAll().whenComplete((serviceResult, throwable) ->
                result.setResult(ResponseEntity.ok(serviceResult)));
        return result;
    }

    @GetMapping("/{id}")
    public DeferredResult<ResponseEntity<String>> getOne(@PathVariable String id){
        DeferredResult<ResponseEntity<String>> result = new DeferredResult<>();
        this.userService.getOne(id).whenComplete((serviceResult, throwable) ->
                result.setResult(ResponseEntity.ok(serviceResult)));
        return result;
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable String id) throws FirebaseAuthException {
        userService.deleteById(id);
    }

}