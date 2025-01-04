package com.example.myapplication;

public class Activity {
    private String name;
    private String time;
    private String description;
    private String location;
    private String google_maps_link;
    private int estimated_price_usd;

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getGoogleMapsLink() {
        return google_maps_link;
    }

    public int getEstimatedPriceUsd() {
        return estimated_price_usd;
    }
}
