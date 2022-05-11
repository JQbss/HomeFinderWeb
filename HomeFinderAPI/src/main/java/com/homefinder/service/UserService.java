package com.homefinder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.homefinder.Util.CRUDUtil;
import com.homefinder.model.Announcement;
import com.homefinder.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@EnableAsync
public class UserService {

    private final FirebaseAuth firebaseAuth;
    private final FirebaseDatabase firebaseDatabase;
    private final AuthService authService;
    private final AnnouncementService announcementService;
    DatabaseReference userRef;


    public UserService(FirebaseAuth firebaseAuth, FirebaseDatabase firebaseDatabase, AuthService authService, AnnouncementService announcementService) {
        this.firebaseAuth = firebaseAuth;
        this.firebaseDatabase = firebaseDatabase;
        this.authService = authService;
        this.announcementService = announcementService;
        DatabaseReference ref = firebaseDatabase.getReference();
        userRef= ref.child("users");
    }

    public UserRecord addUser(User user) throws FirebaseAuthException {
        UserRecord newUserRecord = firebaseAuth.createUser(new UserRecord.CreateRequest().setEmail(user.getEmail()).setPassword(user.getPassword()));
        User newUser= new User();
        newUser.setEmail(newUserRecord.getEmail());
        userRef.child(newUserRecord.getUid()).setValueAsync(newUser);
        return newUserRecord;
    }

    public CompletableFuture<String> findAll(int page, int limit, String orderBy) {
        return CRUDUtil.findAll(userRef,page,limit,orderBy);
    }

    public CompletableFuture<String> getOne(String id) {
      return CRUDUtil.getOne(userRef,id);
    }

    public void deleteById(String id) throws FirebaseAuthException {
        firebaseAuth.deleteUser(id);
    }
    @Async
    public void patch(String id, User user) {
        ObjectMapper oMapper = new ObjectMapper();
        Map newMap  = oMapper.convertValue(user, Map.class);
        getOne(id).whenComplete((serviceResult, throwable) -> {
            try {
                Map<String, Object> one = oMapper.readValue(serviceResult, Map.class);
                one.remove("uid");
                for (Object kv : one.keySet()) {
                    if(newMap.get(kv) != null){
                        one.put(kv.toString(), newMap.get(kv));
                    }
                }
                for (Object kv : newMap.keySet()) {
                    if(one.get(kv)==null){
                        one.put(kv.toString(), newMap.get(kv));
                    }
                }
                userRef.child(id).updateChildrenAsync(one);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    @Async
    public void addToFavorite(String id) {
        User user = authService.getUser();
        List<Announcement> favorite = user.getFavorite();
        if(favorite == null){
            favorite = new ArrayList<>();
        }
        user.setFavorite(favorite);
        patch(user.getUid(),user);
    }

    public CompletableFuture<String> getFavorite() {
        return CRUDUtil.getOne(userRef.child("favorite"),authService.getUser().getUid());
    }
}
