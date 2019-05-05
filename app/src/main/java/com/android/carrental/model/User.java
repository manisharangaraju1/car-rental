package com.android.carrental.model;

public class User {
    private final String name;
    private final String address;
    private final String email;
    private final String phoneNumber;
    private final String CardId;
    private final String id;

    public User() {
        this.name = "";
        this.address = "";
        this.email = "";
        this.id = "";
        this.phoneNumber="";
        this.CardId="";
    }

    public User(String id, String CardId, String name, String email, String phoneNumber, String address) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber=phoneNumber;
        this.id = id;
        this.CardId=CardId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCardId() {
        return CardId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }
}
