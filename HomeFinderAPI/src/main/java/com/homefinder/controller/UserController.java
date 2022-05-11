package com.homefinder.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.homefinder.model.User;
import com.homefinder.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) throws FirebaseAuthException {
        userService.addUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getUid())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<String>> all(@RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "limit", defaultValue = "25") int limit,
                                                      @RequestParam(value = "orderBy", defaultValue = "uid") String orderBy){
        DeferredResult<ResponseEntity<String>> result = new DeferredResult<>();
        this.userService.findAll(page,limit,orderBy).whenComplete((serviceResult, throwable) ->
                result.setResult(ResponseEntity.ok(serviceResult)));
        return result;
    }

    @RequestMapping(method = RequestMethod.GET,path = "/{id}")
    public DeferredResult<ResponseEntity<String>> getOne(@PathVariable String id){
        DeferredResult<ResponseEntity<String>> result = new DeferredResult<>();
        this.userService.getOne(id).whenComplete((serviceResult, throwable) ->
                result.setResult(ResponseEntity.ok(serviceResult)));
        return result;
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "/{id}")
    void deleteEmployee(@PathVariable String id) throws FirebaseAuthException {
        userService.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/announcement/favorite/{id}")
    ResponseEntity<?> addAnnouncementToFavorite(@PathVariable String id) {
        userService.addToFavorite(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.GET, path = "/announcement/favorite")
    ResponseEntity<?> favoriteAnnouncement() {
        return ResponseEntity.ok(userService.getFavorite());
    }

}