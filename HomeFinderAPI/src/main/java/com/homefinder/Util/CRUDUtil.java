package com.homefinder.Util;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CRUDUtil {

    public static CompletableFuture<String> findAll(DatabaseReference ref){
        final String[] allJSON = new String[1];
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                allJSON[0] = new Gson().toJson(document);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        return CompletableFuture.supplyAsync( () ->{
            try { TimeUnit.SECONDS.sleep(2); }
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
                getOne[0] = new Gson().toJson(document);
                System.out.println(getOne[0]);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        return CompletableFuture.supplyAsync( () ->{
            try { TimeUnit.SECONDS.sleep(2); }
            catch (Exception io){ throw new RuntimeException(io); }
            finally { return getOne[0];}
        });
    }

}
