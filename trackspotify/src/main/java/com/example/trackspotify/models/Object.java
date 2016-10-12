package com.example.trackspotify.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luillo on 26/09/16.
 */
public class Object {

    @SerializedName("albums")
    private Album albums;

    public Album getAlbums() {
        return albums;
    }

    public void setAlbums(Album albums) {
        this.albums = albums;
    }
}
