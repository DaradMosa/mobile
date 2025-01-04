package com.example.myapplication;

public class ItineraryPa {

    String name, time, description, location, googleMapsLink;

    public ItineraryPa(String name, String time, String description, String location) {
        this(name, time, description, location, ""); // Calling the other constructor with a default empty string
    }
    public ItineraryPa(String name, String time, String description, String location, String googleMapsLink) {
        this.name = name;
        this.time = time;
        this.description = description;
        this.location = location;
        this.googleMapsLink = googleMapsLink;
    }

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
        return googleMapsLink;
    }
}
