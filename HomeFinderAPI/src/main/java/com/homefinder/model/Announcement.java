package com.homefinder.model;

import com.homefinder.model.announcementEnums.*;

import javax.annotation.Nullable;
import java.util.List;

public class Announcement {
    private String Uid;
    private AnnouncementType type;
    private AnnouncementCategory category;
    private String title;
    private String description;
    private NumberOfRooms numberOfRooms;
    private Level level;
    @Nullable
    private boolean furnished;
    @Nullable
    private long price;
    @Nullable
    private boolean priceIsNegotiable;
    private TypeOfBuilding typeOfBuilding;
    @Nullable
    private long additionalRent;
    @Nullable
    private int area;
    private String localization;
    private String sellerUid;
    private String link;
    private List<String> imageLinks;
    private AnnouncementStatus status;
    @Nullable
    private int yearOfBuilding;
    private AnnouncementMarket market;
    private AnnouncementAdditionalArea additionalArea;

    public Announcement() {

    }

    public boolean isFurnished() {
        return furnished;
    }

    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    public boolean isPriceIsNegotiable() {
        return priceIsNegotiable;
    }

    public void setPriceIsNegotiable(boolean priceIsNegotiable) {
        this.priceIsNegotiable = priceIsNegotiable;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public AnnouncementType getType() {
        return type;
    }

    public void setType(AnnouncementType type) {
        this.type = type;
    }

    public AnnouncementCategory getCategory() {
        return category;
    }

    public void setCategory(AnnouncementCategory category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NumberOfRooms getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(NumberOfRooms numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public TypeOfBuilding getTypeOfBuilding() {
        return typeOfBuilding;
    }

    public void setTypeOfBuilding(TypeOfBuilding typeOfBuilding) {
        this.typeOfBuilding = typeOfBuilding;
    }

    public long getAdditionalRent() {
        return additionalRent;
    }

    public void setAdditionalRent(long additionalRent) {
        this.additionalRent = additionalRent;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public String getSellerUid() {
        return sellerUid;
    }

    public void setSellerUid(String sellerUid) {
        this.sellerUid = sellerUid;
    }

    public List<String> getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(List<String> imageLinks) {
        this.imageLinks = imageLinks;
    }

    public AnnouncementStatus getStatus() {
        return status;
    }

    public void setStatus(AnnouncementStatus status) {
        this.status = status;
    }

    public int getYearOfBuilding() {
        return yearOfBuilding;
    }

    public void setYearOfBuilding(int yearOfBuilding) {
        this.yearOfBuilding = yearOfBuilding;
    }

    public AnnouncementMarket getMarket() {
        return market;
    }

    public void setMarket(AnnouncementMarket market) {
        this.market = market;
    }

    public AnnouncementAdditionalArea getAdditionalArea() {
        return additionalArea;
    }

    public void setAdditionalArea(AnnouncementAdditionalArea additionalArea) {
        this.additionalArea = additionalArea;
    }
}
