package com.homefinder.model.announcementEnums;

public class AnnouncementType {

    final public static AnnouncementType APARTMENT;
    final public static AnnouncementType HOUSE;
    final public static AnnouncementType GARAGE;
    final public static AnnouncementType ANOTHER;

    static {
        APARTMENT = new AnnouncementType("0");
        HOUSE = new AnnouncementType("1");
        GARAGE = new AnnouncementType("2");
        ANOTHER = new AnnouncementType("3");
    }

    final String enumValue;

    private AnnouncementType(String value){
        enumValue = value;
    }

    @Override
    public String toString(){
        return enumValue;
    }
}
