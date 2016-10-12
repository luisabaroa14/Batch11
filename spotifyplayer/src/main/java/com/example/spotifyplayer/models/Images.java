package com.example.spotifyplayer.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luillo on 06/10/16.
 */
public class Images {

    @SerializedName("url")
    public String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Images{" +
                "url='" + url + '\'' +
                '}';
    }
}
