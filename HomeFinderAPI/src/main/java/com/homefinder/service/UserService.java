package com.homefinder.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.homefinder.Util.CRUDUtil;
import com.homefinder.model.User;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

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

    public CompletableFuture<String> findAll(int page, int limit) {
        return CRUDUtil.findAll(userRef,page,limit);
    }

    public CompletableFuture<String> getOne(String id) {
      return CRUDUtil.getOne(userRef,id);
    }

    public void deleteById(String id) throws FirebaseAuthException {
        firebaseAuth.deleteUser(id);
    }
}
