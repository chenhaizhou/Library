package com.thoughtworks.dolphin.model;

/**
 * Created by lzwu on 9/28/14.
 */
public class Image {
    private int imageId;
    private String imageUrl;

    public Image(int imageId, String imageUrl) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public int getImageId() {
        return imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
