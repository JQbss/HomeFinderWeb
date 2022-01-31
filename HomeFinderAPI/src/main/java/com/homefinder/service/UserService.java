package com.homefinder.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.*;
import com.google.gson.Gson;
import com.homefinder.Util.CRUDUtil;
import com.homefinder.model.User;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@EnableAsync
public class UserService {

    private final FirebaseAuth firebaseAuth;
    private final FirebaseDatabase firebaseDatabase;
    DatabaseReference userRef;


    public UserService(FirebaseAuth firebaseAuth, FirebaseDatabase firebaseDatabase) {
        this.firebaseAuth = firebaseAuth;
        this.firebaseDatabase = firebaseDatabase;
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

    public void getUser(){

    }

    public CompletableFuture<String> findAll() {
        return CRUDUtil.findAll(userRef);
    }

    public CompletableFuture<String> getOne(String id) {
      return CRUDUtil.getOne(userRef,id);
    }

    public void deleteById(String id) throws FirebaseAuthException {
        firebaseAuth.deleteUser(id);
    }
}
