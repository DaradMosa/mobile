package com.example.myapplication;

import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

