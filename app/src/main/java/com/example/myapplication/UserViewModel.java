package com.example.myapplication;

import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private String username,email;

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
}

