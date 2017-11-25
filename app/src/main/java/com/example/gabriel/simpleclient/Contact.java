package com.example.gabriel.simpleclient;

/**
 * Created by gabriel on 17-11-25.
 */

public class Contact {

    private String name;
    private int imageId;

    public Contact(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

}
