package com.example.demofx.global;

public class UserHolder {

    private static UserHolder instance = new UserHolder();
    private UserHolder() {}
    public static UserHolder getInstance() {
        return instance;
    }

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
