package com.example.myntrahackathon.ModalClasses;

public class ExchangeCard {
    String image;
    String title;

    public ExchangeCard() {
    }

    public ExchangeCard(String image, String title) {
        this.image = image;
        this.title = title;
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
