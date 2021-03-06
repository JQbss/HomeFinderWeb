package com.homefinder.Util;

import com.google.firebase.database.*;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CRUDUtil {

    public static String findAll(DatabaseReference ref,
                                                    int page,
                                                    int limit,
                                                    String orderBy,
                                                    String favorite) throws ExecutionException, InterruptedException {
        //final String[] allJSON = new String[1];
        final CompletableFuture<String>[] allJSON = new CompletableFuture[]{new CompletableFuture<>()};

        ref.orderByChild(orderBy).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                int currentIndex = 0;
                int indexOfElement = limit*page;
                List<Map<Object, Object>> listOfObject = new ArrayList<>();
                for (Object key : ((HashMap) document).keySet()) {
                    if (key instanceof String) {
                        Object data = dataSnapshot.child((String) key).getValue();
                        Map<Object, Object> map = new HashMap<>();
                        map.put("uid", key);
                        if(favorite!=null && favorite.contains(key.toString())){
                            map.put("favorite", true);
                        }
                        for (Object kv :((HashMap) data).keySet()) {
                            map.put(kv, ((HashMap)data).get(kv));
                        }
                        if(currentIndex >= indexOfElement && currentIndex < indexOfElement+ limit ) {
                            listOfObject.add(map);
                        }
                        currentIndex++;
                    }
                }

                List<Map<Object, Object>> listOfObject2 = new ArrayList<>();
                Map<Object, Object> map = new HashMap<>();
                map.put("items",listOfObject);
                map.put("totalItems",currentIndex);
                int totalNumOfPge = currentIndex/limit;
                map.put("totalPages", totalNumOfPge);
                map.put("currentPage", page);
                map.put("limit",limit);
                listOfObject2.add(map);
                allJSON[0].complete(new Gson().toJson(listOfObject2));
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        return allJSON[0].get();
    }

    public static String findAllWithFilter(DatabaseReference ref,
                                                              int page,
                                                              int limit,
                                                              String orderBy,
                                                              Map<String, Object> filters,
                                                              String favorite) throws ExecutionException, InterruptedException {
        final CompletableFuture<String>[] allJSON = new CompletableFuture[]{new CompletableFuture<>()};
        ref.orderByChild(orderBy).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                List<Map<Object, Object>> listOfObject = new ArrayList<>();
                int currentIndex = 0;
                int indexOfElement = limit*page;
                for (Object key : ((HashMap) document).keySet()) {
                    if (key instanceof String) {
                        Object data = dataSnapshot.child((String) key).getValue();
                        Map<Object, Object> map = new HashMap<>();
                        map.put("uid", key);
                        if(favorite!=null && favorite.contains(key.toString())){
                            map.put("favorite", true);
                        }
                        List<Boolean> isContains= new ArrayList<Boolean>();
                        int numOfFilters = 0;

                        List<String> deleteFilter = new ArrayList<>();
                        for (String el : filters.keySet()) {
                            if (el.contains("furnishes__") && filters.get(el).toString().toUpperCase().equals("FALSE")) {
                                deleteFilter.add(el);
                            }
                        }
                        for(String el : deleteFilter){
                            filters.remove(el);
                        }
                        for (Object kv :((HashMap) data).keySet()) {
                            if(filters.containsKey(kv) && !filters.get(kv).toString().isEmpty() && !kv.toString().equals("address") && !kv.toString().equals("furnishes")) {
                                if(filters.get(kv).toString().contains(":")) {
                                    if(filters.get(kv).toString().length()>1) {
                                        String[] tab = new String[2];
                                        if(filters.get(kv).toString().startsWith(":")){
                                            tab[1] = filters.get(kv).toString().split(":")[1];
                                        } else if(filters.get(kv).toString().endsWith(":")){
                                            tab[0] = filters.get(kv).toString().split(":")[0];
                                        }
                                        else {
                                            tab = filters.get(kv).toString().split(":");
                                        }
                                        if(tab[0]==null || tab[0].isEmpty()) {
                                            tab[0] = String.valueOf(Double.MIN_VALUE);
                                        }
                                        if(tab[1]==null || tab[1].isEmpty()) {
                                            tab[1] = String.valueOf(Double.MAX_VALUE);
                                        }
                                        Double[] range = { Double.parseDouble(tab[0]), Double.parseDouble(tab[1]) };
                                        Double field = Double.parseDouble(((HashMap)data).get(kv).toString());
                                        if(field >= range[0] && field <= range[1]) {
                                            isContains.add(true);
                                            numOfFilters++;
                                        } else {
                                            isContains.add(false);
                                        }
                                    }
                                }
                                else if(((HashMap)data).get(kv).toString().toUpperCase().contains(filters.get(kv).toString().toUpperCase())){
                                    isContains.add(true);
                                    numOfFilters++;
                                } else {
                                    isContains.add(false);
                                }
                            }
                            else if(filters.toString().contains("address__") && filters.toString().contains("furnishes__")) {
                                if(((HashMap)data).containsKey("address") && ((HashMap)data).containsKey("furnishes")) {
                                    for (String el : filters.keySet()) {
                                        if(el.contains("address__")  && !filters.get(el).toString().isEmpty()) {
                                            if (((HashMap<?, ?>) ((HashMap<?, ?>) data).get("address")).get(el.split("__")[1]).toString().toUpperCase().contains(filters.get(el).toString().toUpperCase()))
                                            {
                                                isContains.add(true);
                                                numOfFilters++;
                                            }
                                            else {
                                                isContains.add(false);
                                            }
                                        }
                                        else if(el.contains("furnishes__")  && !filters.get(el).toString().isEmpty()) {
                                            if (((HashMap<?, ?>) ((HashMap<?, ?>) data).get("furnishes")).get(el.split("__")[1]).toString().toUpperCase().contains(filters.get(el).toString().toUpperCase()))
                                            {
                                                isContains.add(true);
                                                numOfFilters++;
                                            }
                                            else {
                                                isContains.add(false);
                                            }

                                        }
                                    }
                                } else{
                                    isContains.add(false);
                                }
                            }
                            else if(filters.toString().contains("address__")) {
                                if(((HashMap)data).containsKey("address")) {
                                    for (String el : filters.keySet()) {
                                        if (el.contains("address__") && !filters.get(el).toString().isEmpty()) {
                                            if (((HashMap<?, ?>) ((HashMap<?, ?>) data).get("address")).get(el.split("__")[1]).toString().toUpperCase().contains(filters.get(el).toString().toUpperCase())) {
                                                isContains.add(true);
                                                numOfFilters++;
                                            } else {
                                                isContains.add(false);
                                            }

                                        }
                                    }
                                } else {
                                    isContains.add(false);
                                }
                            }
                            else if(filters.toString().contains("furnishes__")) {
                                if(((HashMap)data).containsKey("furnishes")){
                                    for (String el : filters.keySet()) {
                                        if(el.contains("furnishes__") && !filters.get(el).toString().isEmpty() && filters.get(el).toString()!=null && filters.get(el).toString().toUpperCase()!="FALSE"){
                                            if (((HashMap<?, ?>) ((HashMap<?, ?>) data).get("furnishes")).get(el.split("__")[1]).toString().toUpperCase().contains(filters.get(el).toString().toUpperCase())) {
                                                isContains.add(true);
                                                numOfFilters++;
                                            } else {
                                                isContains.add(false);
                                            }

                                        }
                                    }
                                } else {
                                    isContains.add(false);
                                }

                            }
                            map.put(kv, ((HashMap)data).get(kv));
                        }

                        if((!isContains.contains(false) && numOfFilters>0) ||
                                (filters.get("uid")!=null && ((ArrayList)filters.get("uid")).contains(map.get("uid")))) {
                            currentIndex++;
                            listOfObject.add(map);
                        }
                    }
                }
                List<Map<Object, Object>> listOfObject3 = new ArrayList<>();
                if(listOfObject.size() > indexOfElement && page != 0) {

                    if(listOfObject.size() < indexOfElement+limit) {
                        for (int i = indexOfElement; i < listOfObject.size(); i++) {
                            listOfObject3.add(listOfObject.get(i));
                        }
                    }else{
                        for (int i = indexOfElement; i < indexOfElement+limit; i++) {
                            listOfObject3.add(listOfObject.get(i));
                        }
                    }
                }
                else if(listOfObject.size() > 0 && page == 0) {
                    if(listOfObject.size() < limit) {
                        for (int i = 0; i < listOfObject.size(); i++) {
                            listOfObject3.add(listOfObject.get(i));
                        }
                    }else{
                        for (int i = 0; i < limit; i++) {
                            listOfObject3.add(listOfObject.get(i));
                        }
                    }

                }
                List<Map<Object, Object>> listOfObject2 = new ArrayList<>();
                Map<Object, Object> map = new HashMap<>();
                map.put("items",listOfObject3);
                map.put("totalItems", currentIndex);
                int totalNumOfPge = currentIndex/limit;
                map.put("totalPages", totalNumOfPge);
                map.put("currentPage", page);
                map.put("limit",limit);
                listOfObject2.add(map);
                allJSON[0].complete(new Gson().toJson(listOfObject2));

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        return allJSON[0].get();
    }

    public static String getOne(DatabaseReference ref, String id) throws ExecutionException, InterruptedException {
        final CompletableFuture<String>[] getOne = new CompletableFuture[]{new CompletableFuture<>()};

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.child(id).getValue();
                Map<Object, Object> map = new HashMap<>();
                if(dataSnapshot.child(id).getValue()!=null){
                    map.put("uid", id);
                    for (Object kv :((HashMap) document).keySet()) {
                        map.put(kv, ((HashMap)document).get(kv));
                    }
                }
                getOne[0].complete(new Gson().toJson(map));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        return getOne[0].get();
    }

}
