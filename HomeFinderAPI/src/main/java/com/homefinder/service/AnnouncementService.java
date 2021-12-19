package com.homefinder.service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.homefinder.model.Announcement;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementService {
    final FirebaseDatabase firebaseDatabase;

    public AnnouncementService(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }

    public void add(Announcement announcement){
            DatabaseReference ref = firebaseDatabase.getReference();
            DatabaseReference announcementRef = ref.child("announcement");
            announcementRef.push().setValueAsync(announcement);
    }
}
