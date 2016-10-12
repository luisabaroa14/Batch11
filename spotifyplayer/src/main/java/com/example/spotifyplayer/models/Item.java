package com.example.spotifyplayer.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luillo on 06/10/16.
 */
public class Item {

    @SerializedName("preview_url")
    public String url;

    @SerializedName("name")
    public String name;

    @SerializedName("album")
    public Album album;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Item{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", album=" + album +
                '}';
    }
}
