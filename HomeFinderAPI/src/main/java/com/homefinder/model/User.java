package com.homefinder.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class User implements Serializable {
    private String username;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String Uid;
    private Role role;
    private Address address;
    @JsonProperty
    private List<String> favorites;
    private boolean EmailVerified;

    public User(String username, String email, String phoneNumber, String firstName, String lastName, String uid, Role role, Address address, List<String> favorite, boolean emailVerified) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        Uid = uid;
        this.role = role;
        this.address = address;
        this.favorites = favorite;
        EmailVerified = emailVerified;
    }

    public User() {
    }

    public List<String> getFavorite() {
        return favorites;
    }

    public void setFavorite(List<String> favorite) {
        this.favorites = favorite;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEmailVerified() {
        return EmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        EmailVerified = emailVerified;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

}
