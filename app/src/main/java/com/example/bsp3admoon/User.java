package com.example.bsp3admoon;

public class User {


    private int userId;
    private String name;
    private String address;
    private String email;
    private long phoneNum;
    //private long credit;

    //for initialization purposes

    public User() {
    }

    public User(int userId, String name, String address, String email, long phoneNum) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }
}

