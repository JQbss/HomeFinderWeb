package com.homefinder.service;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.*;
import com.homefinder.Util.CRUDUtil;
import com.homefinder.model.Announcement;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.CompletableFuture;

@Service
@EnableAsync
public class AnnouncementService{
    final FirebaseDatabase firebaseDatabase;
    DatabaseReference announcementRef;

    public AnnouncementService(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
        DatabaseReference ref = firebaseDatabase.getReference();
        announcementRef= ref.child("announcement");
    }

    public void add(Announcement announcement){
        announcementRef.push().setValueAsync(announcement);
    }

    public void deleteById(String id) throws FirebaseAuthException {
        announcementRef.child(id).removeValueAsync();
    }

    @Async
    public CompletableFuture<String> getAll() {
        return CRUDUtil.findAll(announcementRef);
    }

    @Async
    public CompletableFuture<String> getOne(String id) {
       return CRUDUtil.getOne(announcementRef,id);
    }
}
