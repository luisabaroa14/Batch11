package com.example.spotifyplayer;

import com.example.spotifyplayer.models.object;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Luillo on 04/10/16.
 */
public interface TrackArtist {

    @GET("/v1/search?type=track")
    Call<object> getTrack(@Query("q") String trackName);
}
