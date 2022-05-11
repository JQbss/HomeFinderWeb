package com.homefinder.service;

import com.fasterxml.jackson.core.JsonParser;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@EnableAsync
public class UserService {

    private final FirebaseAuth firebaseAuth;
    private final FirebaseDatabase firebaseDatabase;
    @Autowired
    private AuthService authService;
    private final AnnouncementService announcementService;
    DatabaseReference userRef;
    DatabaseReference announcementRef;

    public UserService(FirebaseAuth firebaseAuth, FirebaseDatabase firebaseDatabase, AnnouncementService announcementService) {
        this.firebaseAuth = firebaseAuth;
        this.firebaseDatabase = firebaseDatabase;
        this.announcementService = announcementService;
        DatabaseReference ref = firebaseDatabase.getReference();
        userRef= ref.child("users");
        announcementRef = ref.child("announcement");
    }

    public UserRecord addUser(User user) throws FirebaseAuthException {
        UserRecord newUserRecord = firebaseAuth.createUser(new UserRecord.CreateRequest().setEmail(user.getEmail()));
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
    public int update(String id, User user) throws FirebaseAuthException {
        if(announcementRef.child(id)==null){
            addUser(user);
            return 201;
        }
        announcementRef.child(id).push().setValueAsync(user);
        return 200;
    }

    public void addToFavorite(String id) {
        userRef.child(authService.getUser().getUid()).child("favorites").push().setValueAsync(id);
////        ObjectMapper oMapper = new ObjectMapper();
////        getOne(uid).whenComplete((serviceResult, throwable) -> {
////            //user[0] = oMapper.convertValue(serviceResult, User.class);
////            System.out.println("ELOOOO");
////            System.out.println(serviceResult);
////            //System.out.println(user[0].getFavorite().toString());
////        });
////
////        System.out.println("ELOOOO345345");
////        List<String> favorite = user[0].getFavorite();
////        if(favorite == null) {
////            favorite = new ArrayList<>();
////        }
////        favorite.add(id);
////        user[0].setFavorite(favorite);
////        patch(user[0].getUid(), user[0]);
    }

    public void getFavorite(String uid) {

        getOne(uid).whenComplete((serviceResult, throwable) -> {
            ObjectMapper oMapper = new ObjectMapper();
            System.out.println("ELOOOO");
            try {
                Map<String, Object> users = oMapper.readValue(serviceResult, Map.class);
                System.out.println(users.toString());
                Map<String, Object> fav = oMapper.convertValue(users.get("favorites"), Map.class);
                Map<String,String> fid = new HashMap<>();

                for (var key:fav.keySet()) {
                    fid.put("uid",fav.get(key).toString());
                }
                var el = announcementRef.child(fid.get(0));
                System.out.println(el);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        //userRef.child().child("favorites");
    }
}
