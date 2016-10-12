package com.example.spotifyartist.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luillo on 23/09/16.
 */
public class Artist {

    @SerializedName("href")
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @SerializedName("items")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}


