package com.homefinder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homefinder.Util.CRUDUtil;
import com.homefinder.model.Announcement;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@EnableAsync
public class AnnouncementService{
    final FirebaseDatabase firebaseDatabase;
    final AuthService authService;
    DatabaseReference announcementRef;

    public AnnouncementService(FirebaseDatabase firebaseDatabase, AuthService authService) {
        this.firebaseDatabase = firebaseDatabase;
        this.authService = authService;
        DatabaseReference ref = firebaseDatabase.getReference();
        announcementRef= ref.child("announcement");
    }
    public void add(Announcement announcement){
        announcementRef.push().setValueAsync(announcement);
    }

    public void add(List<Announcement> announcement){
        announcement.forEach(el ->{
            add(el);
        });
    }

    public int update(String id,Announcement announcement){
        if(announcementRef.child(id)==null){
            add(announcement);
            return 201;
        }
        announcementRef.child(id).push().setValueAsync(announcement);
        return 200;
    }

    @Async
    public void patch(String id, Announcement announcement) {
        ObjectMapper oMapper = new ObjectMapper();
        Map newAnnouncement  = oMapper.convertValue(announcement, Map.class);
        getOne(id).whenComplete((serviceResult, throwable) -> {
            try {
                Map<String, Object> one = oMapper.readValue(serviceResult, Map.class);
                one.remove("uid");
                for (Object kv : one.keySet()) {
                    if(newAnnouncement.get(kv) != null){
                        one.put(kv.toString(), newAnnouncement.get(kv));
                    }
                }
                for (Object kv : newAnnouncement.keySet()) {
                    if(one.get(kv)==null){
                        one.put(kv.toString(), newAnnouncement.get(kv));
                    }
                }
                announcementRef.child(id).updateChildrenAsync(one);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    public String deleteById(String id) throws FirebaseAuthException {
        if(announcementRef.child(id)==null){
            announcementRef.child(id).removeValueAsync();
            return id;
        }
        return null;
    }

    @Async
    public CompletableFuture<String> getAll(int page, int limit, String orderBy, Map<String, Object> filters) {
        if(!filters.isEmpty()){
            return CRUDUtil.findAllWithFilter(announcementRef,page,limit,orderBy,filters);
        }
        return CRUDUtil.findAll(announcementRef,page,limit,orderBy);
    }

    @Async
    public CompletableFuture<String> getOne(String id) {
       return CRUDUtil.getOne(announcementRef,id);
    }

}
