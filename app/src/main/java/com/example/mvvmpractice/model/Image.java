package com.example.mvvmpractice.model;

public class Image {
    private String user;
    private String largeImageURL;

    public Image(String user, String largeImageURL) {
        this.user = user;
        this.largeImageURL = largeImageURL;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }
}
