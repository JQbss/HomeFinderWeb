package com.homefinder.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String Uid;
    private Role role;
    private Address address;
    private List<Announcement> favorite;
    private boolean EmailVerified;

    public User(String username, String email, String password, String phoneNumber, String firstName, String lastName, String uid, Role role, Address address, List<Announcement> favorite, boolean emailVerified) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        Uid = uid;
        this.role = role;
        this.address = address;
        this.favorite = favorite;
        EmailVerified = emailVerified;
    }

    public User() {
    }

    public List<Announcement> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<Announcement> favorite) {
        this.favorite = favorite;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Uid='" + Uid + '\'' +
                ", role=" + role +
                ", address=" + address +
                ", favorite=" + favorite +
                ", EmailVerified=" + EmailVerified +
                '}';
    }
}
