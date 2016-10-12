package com.example.spotifyartist;

import android.text.Editable;

import com.example.spotifyartist.models.Artist;
import com.example.spotifyartist.models.RequestArtist;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Luillo on 23/09/16.
 */
public interface SpotifyArtistClient {


//        @GET("v1/search?type=artist")
//        Call<Artist> getArtist(@Query("q") String artistName);

    @GET("/v1/search?type=artist")
    Call<RequestArtist> getArtist(@Query("q") String artistName);
}


