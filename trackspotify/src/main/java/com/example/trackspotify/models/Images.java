package com.example.trackspotify.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luillo on 26/09/16.
 */
public class Images {

    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

