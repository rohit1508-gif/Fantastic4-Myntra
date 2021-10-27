package com.example.myntrahackathon.ModalClasses;

public class ExchangeCard {
    String image;
    String title;
    String status;

    public ExchangeCard() {
    }

    public ExchangeCard(String image, String title, String status) {
        this.image = image;
        this.title = title;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
