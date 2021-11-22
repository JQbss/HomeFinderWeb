package com.homefinder.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.homefinder.config.FirebaseConfig;
import com.homefinder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private FirebaseConfig firebaseConfig;

    public UserRecord addUser(User user) throws FirebaseAuthException {
        System.out.println(user.getEmail()+"   "+user.getPassword());
        return firebaseConfig.firebaseAuth().createUser(new UserRecord.CreateRequest().setEmail(user.getEmail()).setPassword(user.getPassword()));
    }
}
