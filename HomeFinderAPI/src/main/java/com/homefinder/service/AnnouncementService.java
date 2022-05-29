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

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@EnableAsync
public class AnnouncementService{
    final FirebaseDatabase firebaseDatabase;
    final AuthService authService;
    final UserService userService;
    DatabaseReference announcementRef;

    public AnnouncementService(FirebaseDatabase firebaseDatabase, AuthService authService, UserService userService) {
        this.firebaseDatabase = firebaseDatabase;
        this.authService = authService;
        this.userService = userService;
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

    public void patch(String id, Announcement announcement) throws ExecutionException, InterruptedException {
        ObjectMapper oMapper = new ObjectMapper();
        Map newAnnouncement  = oMapper.convertValue(announcement, Map.class);
        String serviceResult = getOne(id);
        try {
            Map<String, Object> one = oMapper.readValue(serviceResult, Map.class);
            one.remove("uid");
            if(one.containsKey("favorite")){
                one.remove("favorite");
            }
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
    }

    public String deleteById(String id) throws FirebaseAuthException {
        if(announcementRef.child(id)==null){
            announcementRef.child(id).removeValueAsync();
            return id;
        }
        return null;
    }

    public String getAll(int page, int limit, String orderBy, Map<String, Object> filters, String userId) throws ExecutionException, InterruptedException, JsonProcessingException {
        if(userId != null && filters!=null && !filters.isEmpty()) {
            String favorite = userService.getFavoriteId(userId);
            if(favorite!=null)
                return CRUDUtil.findAllWithFilter(announcementRef, page, limit, orderBy, filters,favorite);
            return CRUDUtil.findAllWithFilter(announcementRef, page, limit, orderBy, filters,null);
        }
        if(userId != null) {
            String favorite = userService.getFavoriteId(userId);
            if(favorite!=null)
                return CRUDUtil.findAll(announcementRef, page, limit, orderBy, favorite);
            return CRUDUtil.findAll(announcementRef, page, limit, orderBy,null);
        }
        if(filters!=null && !filters.isEmpty()) {
            return CRUDUtil.findAllWithFilter(announcementRef, page, limit, orderBy, filters,null);
        }
        return CRUDUtil.findAll(announcementRef,page,limit,orderBy,null);
    }

    public String getOne(String id) throws ExecutionException, InterruptedException {
       return CRUDUtil.getOne(announcementRef,id);
    }

}
