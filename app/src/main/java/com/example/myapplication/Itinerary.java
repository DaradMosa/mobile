package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Itinerary {
    @SerializedName("date")
    private String date;

    @SerializedName("activities")
    private List<Activity> activities;

    public String getDate() {
        return date;
    }

    public List<Activity> getActivities() {
        return activities;
    }
}
