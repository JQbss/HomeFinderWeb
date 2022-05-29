package com.homefinder.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.homefinder.dto.LoginRequest;
import com.homefinder.model.Announcement;
import com.homefinder.model.User;
import com.homefinder.service.AuthService;
import com.homefinder.service.UserService;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/users")
public class UserController {

    final UserService userService;
    final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ResponseEntity<?> createUser(@RequestBody LoginRequest loginRequest) throws FirebaseAuthException {
        userService.addUser(loginRequest);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public ResponseEntity<String> all(@RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "limit", defaultValue = "25") int limit,
                                                      @RequestParam(value = "orderBy", defaultValue = "uid") String orderBy) throws ExecutionException, InterruptedException {
        DeferredResult<ResponseEntity<String>> result = new DeferredResult<>();
        String res = this.userService.findAll(page,limit,orderBy);
        if(res!=null){
            return ResponseEntity.ok(res);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET,path = "/{id}", produces = "application/json;charset=utf-8")
    public ResponseEntity<String> getOne(@PathVariable String id) throws ExecutionException, InterruptedException {
        DeferredResult<ResponseEntity<String>> result = new DeferredResult<>();
        String res =   this.userService.getOne(id);
        if(res!=null){
            return ResponseEntity.ok(res);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "/{id}")
    void deleteEmployee(@PathVariable String id) throws FirebaseAuthException {
        userService.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/announcement/favorite/{id}",produces = "application/json;charset=utf-8")
    ResponseEntity<?> addAnnouncementToFavorite(@PathVariable String id) {
        userService.addToFavorite(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/announcement/favorite/{id}")
    ResponseEntity<?> deleteAnnouncementFromFavorite(@PathVariable String id) {
        userService.deleteFromFavorite(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/announcement/favorite", produces = "application/json;charset=utf-8")
    ResponseEntity<String> favoriteAnnouncement() throws ExecutionException, InterruptedException {
        return  ResponseEntity.ok(userService.getFavorite(authService.getUser().getUid()));
    }
    @RequestMapping(method = RequestMethod.PATCH,path = "/{id}", produces = "application/json;charset=utf-8")
    public ResponseEntity<?> patchAnnouncement(@PathVariable String id, @RequestBody User user) throws ExecutionException, InterruptedException {
        userService.patch(id,user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}", produces = "application/json;charset=utf-8")
    public ResponseEntity<?> updateAnnouncement(@PathVariable String id, @RequestBody User user) throws FirebaseAuthException {
        if(userService.update(id,user) == 201) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}