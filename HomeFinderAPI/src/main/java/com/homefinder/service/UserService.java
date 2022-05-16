package com.homefinder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.homefinder.Util.CRUDUtil;
import com.homefinder.dto.LoginRequest;
import com.homefinder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
@EnableAsync
public class UserService {

    private final FirebaseAuth firebaseAuth;
    private final FirebaseDatabase firebaseDatabase;
    @Autowired
    private AuthService authService;
    DatabaseReference userRef;
    DatabaseReference announcementRef;

    public UserService(FirebaseAuth firebaseAuth, FirebaseDatabase firebaseDatabase) {
        this.firebaseAuth = firebaseAuth;
        this.firebaseDatabase = firebaseDatabase;
        DatabaseReference ref = firebaseDatabase.getReference();
        userRef= ref.child("users");
        announcementRef = ref.child("announcement");
    }

    public UserRecord addUser(LoginRequest loginRequest) throws FirebaseAuthException {
        UserRecord newUserRecord = firebaseAuth.createUser(new UserRecord.CreateRequest().setEmail(loginRequest.getEmail()).setPassword(loginRequest.getPassword()));
        User newUser= new User();
        newUser.setEmail(newUserRecord.getEmail());
        userRef.child(newUserRecord.getUid()).setValueAsync(newUser);
        return newUserRecord;
    }

    public CompletableFuture<String> findAll(int page, int limit, String orderBy) {
        return CRUDUtil.findAll(userRef,page,limit,orderBy,null);
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
//        if(announcementRef.child(id)==null) {
//            addUser(user);
//            return 201;
//        }
        announcementRef.child(id).push().setValueAsync(user);
        return 200;
    }

    public void addToFavorite(String id) {
        userRef.child(authService.getUser().getUid()).child("favorites").child(id).setValueAsync(id);
    }

    public void deleteFromFavorite(String id) {
        userRef.child(authService.getUser().getUid()).child("favorites").child(id).removeValueAsync();
    }

    public String getFavoriteId(String uid) throws ExecutionException, InterruptedException {
        final CompletableFuture<String>[] completableFuture2 = new CompletableFuture[]{new CompletableFuture<>()};
        getOne(uid).whenComplete((serviceResult, throwable) -> {
            ObjectMapper oMapper = new ObjectMapper();
            try {
                Map<String, Object> users = oMapper.readValue(serviceResult, Map.class);
                Map<String, Object> fav = oMapper.convertValue(users.get("favorites"), Map.class);
                Map<String, Object> fid = new HashMap<>();
                List<String> listFid = new ArrayList<>();
                for (var key : fav.keySet()) {
                    listFid.add(fav.get(key).toString());
                }
                fid.put("uid", listFid);
                if(listFid==null){
                    completableFuture2[0].complete(null);
                } else {
                    completableFuture2[0].complete(fid.toString());
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return completableFuture2[0].get();
    }
    public String getFavorite(String uid) throws ExecutionException, InterruptedException {
        final CompletableFuture<String>[] completableFuture2 = new CompletableFuture[]{new CompletableFuture<>()};
        Executors.newCachedThreadPool().submit(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getOne(uid).whenComplete((serviceResult, throwable) -> {
                ObjectMapper oMapper = new ObjectMapper();
                try {
                    Map<String, Object> users = oMapper.readValue(serviceResult, Map.class);
                    Map<String, Object> fav = oMapper.convertValue(users.get("favorites"), Map.class);
                    Map<String, Object> fid = new HashMap<>();
                    List<String> listFid = new ArrayList<>();
                    for (var key : fav.keySet()) {
                        listFid.add(fav.get(key).toString());
                    }
                    fid.put("uid", listFid);
                    completableFuture2[0].complete(CRUDUtil.findAllWithFilter(announcementRef,0, 25, "uid", fid,fid.toString()).get());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (IOException | InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        });
        return completableFuture2[0].get();
    }
}
