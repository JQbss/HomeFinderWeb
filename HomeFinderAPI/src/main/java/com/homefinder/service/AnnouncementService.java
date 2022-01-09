package com.homefinder.service;

import com.google.firebase.database.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.homefinder.model.Announcement;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@EnableAsync
public class AnnouncementService{
    final FirebaseDatabase firebaseDatabase;
    DatabaseReference announcementRef;
    String json=null;
    String getOne=null;
    public AnnouncementService(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
        DatabaseReference ref = firebaseDatabase.getReference();
        announcementRef= ref.child("announcement");

        announcementRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                json = new Gson().toJson(document);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    public void add(Announcement announcement){
        announcementRef.push().setValueAsync(announcement);
    }
    public String getAll() {
        return json;
    }

    @Async
    public CompletableFuture<String> getOne(String id) {
        announcementRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.child(id).getValue();
                getOne = new Gson().toJson(document);
                System.out.println(getOne);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        return CompletableFuture.supplyAsync( () ->{
            try { TimeUnit.SECONDS.sleep(2); }
            catch (Exception io){ throw new RuntimeException(io); }
            finally { return getOne;}
        });
    }
}
