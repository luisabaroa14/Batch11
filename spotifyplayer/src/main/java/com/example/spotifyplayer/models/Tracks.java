package com.example.spotifyplayer.models;

import android.content.ClipData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luillo on 04/10/16.
 */
public class Tracks {

    @SerializedName("items")
    @Expose
    public List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Tracks{" +
                "items=" + items +
                '}';
    }
}
