package com.homefinder.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.homefinder.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final FirebaseAuth firebaseAuth;

    public UserService(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    public UserRecord addUser(User user) throws FirebaseAuthException {
        return firebaseAuth.createUser(new UserRecord.CreateRequest().setEmail(user.getEmail()).setPassword(user.getPassword()));
    }
    public void getUser(){

    }

    public List<UserRecord> findAll() {
        return null;
    }

    public UserRecord findById(String id) {
        return null;
    }

    public void deleteById(String id) throws FirebaseAuthException {
        firebaseAuth.deleteUser(id);
    }
}
