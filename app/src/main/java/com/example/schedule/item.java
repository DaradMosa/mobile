package com.example.schedule;

public class item {
    String datess;
    String sched;
    int image;

    public item(String datess,String sched, int image) {
        this.datess = datess;
        this.image = image;
        this.sched = sched;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDatess() {
        return datess;
    }

    public void setName(String name) {
        this.datess = name;
    }

    public String getSched() {
        return sched;
    }

    public void setSched(String sched) {
        this.sched = sched;
    }
}


