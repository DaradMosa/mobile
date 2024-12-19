package com.example.myapplication;

public class pa {
    private int image;
    private String packageName;
     private String budget;

    public pa(int image,String packageName, String budget) {

        this.image = image;
        this.packageName = packageName;
        this.budget = budget;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
       this.packageName = packageName;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
