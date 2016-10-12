package com.example.trackspotify.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luillo on 26/09/16.
 */
public class Album {

    @SerializedName("items")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
