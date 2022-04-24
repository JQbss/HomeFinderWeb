package com.homefinder.model.announcementEnums;

public enum NumberOfRooms {
    KAWALERKA(0),
    TWO(1),
    THREE(2),
    FOURPLUS(3);

    private final int id;
    NumberOfRooms(int id){
        this.id = id;
    }
    public int getValue() { return id; }
}
