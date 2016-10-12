package com.example.spotifyplayer.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luillo on 04/10/16.
 */
public class object {

    @SerializedName("tracks")
    private Tracks tracks;

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "object{" +
                "tracks=" + tracks +
                '}';
    }
}
