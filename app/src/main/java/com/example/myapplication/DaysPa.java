package com.example.myapplication;

import java.util.List;

public class DaysPa {
    String dayNumber, date;
    List<ItineraryPa> activities;

    public DaysPa(String dayNumber, String date, List<ItineraryPa> activities) {
        this.dayNumber = dayNumber;
        this.date = date;
        this.activities = activities;
    }

    public void setDayNumber(String dayNumber){
        this.dayNumber = dayNumber;
    }
    public void setdate(String date){
        this.date = date;
    }
    public String getDayNumber(){
        return dayNumber;
    }
    public String getDate(){
        return date;
    }

}
