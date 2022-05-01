package com.homefinder.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class Announcement {
    private String Uid;
    private Address address;
    private Integer type;
    private Integer category;
    private String title;
    private String description;
    private Integer numberOfRooms;
    private Integer level;
    private Boolean furnished;
    private Long price;
    private Boolean priceIsNegotiable;
    private Integer typeOfBuilding;
    private Long additionalRent;
    private Integer area;
    private String localization;
    private String sellerUid;
    private String link;
    private List<String> imageLinks;
    private Integer status;
    private Integer yearOfBuilding;

    private Integer market;

    private Integer additionalArea;

    public Announcement() {

    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
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

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getFurnished() {
        return furnished;
    }

    public void setFurnished(Boolean furnished) {
        this.furnished = furnished;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getPriceIsNegotiable() {
        return priceIsNegotiable;
    }

    public void setPriceIsNegotiable(Boolean priceIsNegotiable) {
        this.priceIsNegotiable = priceIsNegotiable;
    }

    public Integer getTypeOfBuilding() {
        return typeOfBuilding;
    }

    public void setTypeOfBuilding(Integer typeOfBuilding) {
        this.typeOfBuilding = typeOfBuilding;
    }

    public Long getAdditionalRent() {
        return additionalRent;
    }

    public void setAdditionalRent(Long additionalRent) {
        this.additionalRent = additionalRent;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<String> getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(List<String> imageLinks) {
        this.imageLinks = imageLinks;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getYearOfBuilding() {
        return yearOfBuilding;
    }

    public void setYearOfBuilding(Integer yearOfBuilding) {
        this.yearOfBuilding = yearOfBuilding;
    }

    public Integer getMarket() {
        return market;
    }

    public void setMarket(Integer market) {
        this.market = market;
    }

    public Integer getAdditionalArea() {
        return additionalArea;
    }

    public void setAdditionalArea(Integer additionalArea) {
        this.additionalArea = additionalArea;
    }
}
