package com.example.myntrahackathon.Modal;

public class Redeem {
    String image;
    String title;

    public Redeem(){}

    public Redeem(String image, String title){
        this.image=image;
        this.title=title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
