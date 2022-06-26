package com.utstam.taja;

import com.google.gson.annotations.SerializedName;

public class Account {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("role")
    private String role;

    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
