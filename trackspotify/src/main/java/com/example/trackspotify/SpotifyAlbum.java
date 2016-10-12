package com.example.trackspotify;

import com.example.trackspotify.models.Object;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Luillo on 26/09/16.
 */
public interface SpotifyAlbum {

    @GET("/v1/search?type=album")
    Call<Object> getAlbum(@Query("q") String albumName);

}
