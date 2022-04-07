package com.homefinder.Util;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.homefinder.model.Announcement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CRUDUtil {

    private static final int waitTime = 2;
    public static CompletableFuture<String> findAll(DatabaseReference ref){
        final String[] allJSON = new String[1];
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                List<Map<Object, Object>> listOfObject = new ArrayList<>();
                for (Object key : ((HashMap) document).keySet()) {
                    if (key instanceof String) {
                        Object data = dataSnapshot.child((String) key).getValue();
                        Map<Object, Object> map = new HashMap<>();
                        map.put("uid", key);
                        for (Object kv :((HashMap) data).keySet()) {
                            map.put(kv, ((HashMap)data).get(kv));
                        }
                        listOfObject.add(map);
                    }
                }
                allJSON[0] = new Gson().toJson(listOfObject);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        return CompletableFuture.supplyAsync( () ->{
            try { TimeUnit.SECONDS.sleep(waitTime); }
            catch (Exception io){ throw new RuntimeException(io); }
            finally { return allJSON[0];}
        });
    }

    public static CompletableFuture<String> getOne(DatabaseReference ref, String id){
        final String[] getOne = {null};
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.child(id).getValue();
                Map<Object, Object> map = new HashMap<>();
                map.put("uid", id);
                for (Object kv :((HashMap) document).keySet()) {
                    map.put(kv, ((HashMap)document).get(kv));
                }
                getOne[0] = new Gson().toJson(map);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        return CompletableFuture.supplyAsync( () ->{
            try { TimeUnit.SECONDS.sleep(waitTime); }
            catch (Exception io){ throw new RuntimeException(io); }
            finally { return getOne[0];}
        });
    }
}
