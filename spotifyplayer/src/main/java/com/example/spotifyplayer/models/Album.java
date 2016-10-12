package com.example.spotifyplayer.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luillo on 06/10/16.
 */
public class Album {

    @SerializedName("images")
    public List<Images> images;

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Album{" +
                "images=" + images +
                '}';
    }
}
