package com.example.spotifyartist.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luillo on 26/09/16.
 */
public class RequestArtist {

    @SerializedName("artists")
    private Artist artists;

    public Artist getArtists() {
        return artists;
    }

    public void setArtists(Artist artists) {
        this.artists = artists;
    }
}
